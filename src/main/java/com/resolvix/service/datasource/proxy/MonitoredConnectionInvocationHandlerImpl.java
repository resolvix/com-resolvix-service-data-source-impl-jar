package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredConnection;
import com.resolvix.service.datasource.api.monitor.Availability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.resolvix.service.datasource.SqlExceptionUtils.isConnectionException;

public class MonitoredConnectionInvocationHandlerImpl
    implements InvocationHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredConnectionInvocationHandlerImpl.class);

    private final ReentrantReadWriteLock readWriteLock;

    private Lock readLock;

    private Lock writeLock;

    private final Connection connection;

    private final Monitor<Availability> monitor;

    private final Listener listener;

    private volatile Availability availability;

    protected MonitoredConnectionInvocationHandlerImpl(
        Connection connection, Monitor<Availability> monitor) {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
        this.connection = connection;
        this.monitor = monitor;
        this.listener = new Listener();
        this.monitor.addListener(this.listener);
        availability = monitor.getState();
    }

    public static MonitoredConnectionInvocationHandlerImpl of(
        Connection connection, Monitor<Availability> monitor) {
        return new MonitoredConnectionInvocationHandlerImpl(connection, monitor);
    }

    private final class Listener
        implements com.resolvix.lib.event.api.Listener<Availability>
    {

        @Override
        public void notify(Availability property) {
            MonitoredConnectionInvocationHandlerImpl.this.setAvailability(property);
        }
    }

    private void setAvailability(Availability availability) {
        writeLock.lock();
        try {
            this.availability = availability;
        } finally {
            writeLock.unlock();
        }
    }

    private void handleSqlConnectionException(SQLException e)
    {
        setAvailability(Availability.DOWN);
        // prompt monitor to test connection
    }

    private void handleSqlException(SQLException e)
    {
        if (isConnectionException(e))
            handleSqlConnectionException(e);
    }

    private void handleInvocationTargetException(InvocationTargetException e)
        throws Throwable
    {
        Throwable cause = e.getCause();
        if (cause instanceof SQLException)
            handleSqlException((SQLException) cause);
    }

    private Object invokeMonitorConnection(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getAvailability".equals(method.getName()))
            return availability;

        throw new UnsupportedOperationException();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.debug("{} called.", method.getName());
        try {
            Class<?> methodDeclaringClass = method.getDeclaringClass();
            if (Connection.class.equals(methodDeclaringClass))
                return method.invoke(connection, args);

            if (MonitoredConnection.class.equals(methodDeclaringClass))
                return invokeMonitorConnection(proxy, method, args);

            throw new UnsupportedOperationException();
        } catch (InvocationTargetException e) {
            handleInvocationTargetException(e);
            throw e.getCause();
        } finally {

        }
    }
}
