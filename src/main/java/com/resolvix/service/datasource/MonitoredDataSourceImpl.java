package com.resolvix.service.datasource;

import com.resolvix.service.datasource.api.MonitoredDataSource;
import com.resolvix.service.datasource.api.event.AvailabilityChange;
import com.resolvix.service.datasource.api.event.Change;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.api.monitor.Performance;
import com.resolvix.service.datasource.api.monitor.Reliability;
import com.resolvix.service.datasource.event.AvailabilityChangeImpl;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class MonitoredDataSourceImpl
    implements MonitoredDataSource<Availability, Reliability, Performance>
{
    private static final String SQLSTATE_CONNECTION_EXCEPTION_CLASS = "08";

    private final ReadWriteLock readWriteLock;

    private final Lock readLock;

    private final Lock writeLock;

    private final DataSource dataSource;

    private final Deque<AvailabilityChange<Availability>> availabilityChanges;

    private volatile Availability availability;

    private volatile int connectionFailures;

    private MonitoredDataSourceImpl(DataSource dataSource, Availability initialAvailability) {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
        this.dataSource = dataSource;
        this.availabilityChanges = new ArrayDeque<>();
        this.availability = initialAvailability;
        this.connectionFailures = 0;
    }

    public static MonitoredDataSourceImpl of(DataSource dataSource, Availability initialAvailability) {
        return new MonitoredDataSourceImpl(dataSource, initialAvailability);
    }

    private class Listener
        implements com.resolvix.lib.monitor.api.Listener<Availability>
    {

        @Override
        public void signal(Availability property) {
            MonitoredDataSourceImpl.this.setAvailability(property);
        }
    }

    private void processConnectionFailure(SQLException e) {
        String sqlState = e.getSQLState();
        if (!sqlState.startsWith(SQLSTATE_CONNECTION_EXCEPTION_CLASS))
            return;

        writeLock.lock();
        try {
            connectionFailures++;
            if (connectionFailures > 5)
                setAvailability(Availability.DOWN);
        } finally {
            writeLock.unlock();
        }
    }

    //
    //  DataSource
    //

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        SQLException sqlException = null;
        boolean decConnectionFailures = false;
        readLock.lock();
        try {
            if (!availability.isUp())
                throw new SQLException();

            try {
                connection = dataSource.getConnection();
                decConnectionFailures = (connectionFailures > 0);
            } catch (SQLException e) {
                sqlException = e;
            }
        } finally {
            readLock.unlock();
        }

        if (sqlException != null) {
            processConnectionFailure(sqlException);
            throw sqlException;
        }

        if (decConnectionFailures) {
            writeLock.lock();
            try {
                connectionFailures--;
            } finally {
                writeLock.unlock();
            }
        }

        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        readLock.lock();
        try {
            if (!availability.isUp())
                throw new SQLException();

            try {
                return dataSource.getConnection(username, password);
            } catch (SQLException e) {
                processConnectionFailure(e);
                throw e;
            }
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    //
    //  CommonDataSource
    //

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    //
    //  Wrapper
    //

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    //
    //  MonitoredDataSource
    //

    @Override
    public Availability getAvailability() {
        readLock.lock();
        try {
            return availability;
        } finally {
            readLock.unlock();
        }
    }

    protected void setAvailability(Availability availability) {
        writeLock.lock();
        try {
            AvailabilityChange<Availability> availabilityChange
                = AvailabilityChangeImpl.of(this.availability, availability, Instant.now());
            this.availabilityChanges.add(availabilityChange);
            this.availability = availability;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Reliability getReliability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Performance getPerformance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized boolean isUp() {
        readLock.lock();
        try {
            return availability.isUp();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public synchronized boolean isDown() {
        readLock.lock();
        try {
            return availability.isDown();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Duration getUptime() {
        readLock.lock();
        try {
            return null;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Duration getDowntime() {
        readLock.lock();
        try {
            return null;
        } finally {
            readLock.unlock();
        }
    }

    //
    //  RecentChangeHistory
    //

    @Override
    public List<Change> getRecentChangeHistory() {
        readLock.lock();
        try {
            return Collections.unmodifiableList(
                new ArrayList<>(availabilityChanges));
        } finally {
            readLock.unlock();
        }
    }
}
