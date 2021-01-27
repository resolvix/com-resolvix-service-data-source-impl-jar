package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredConnection;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.base.BaseStaticProxyImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.resolvix.service.datasource.SqlExceptionUtils.isTransientConnectionException;

public class MonitoredConnectionImpl
    extends BaseStaticProxyImpl
    implements MonitoredConnection
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredConnectionInvocationHandlerImpl.class);

    private final ReentrantReadWriteLock readWriteLock;

    private Lock readLock;

    private Lock writeLock;

    private final Connection connection;

    private final Monitor<Availability> monitor;

    private final MonitoredConnectionImpl.Listener listener;

    private volatile Availability availability;

    protected MonitoredConnectionImpl(
        Connection connection, Monitor<Availability> monitor) {
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
        this.connection = connection;
        this.monitor = monitor;
        this.listener = new MonitoredConnectionImpl.Listener();
        this.monitor.addListener(this.listener);
        availability = monitor.getState();
    }

    public static MonitoredConnectionImpl of(
        Connection connection, Monitor<Availability> monitor) {
        return new MonitoredConnectionImpl(connection, monitor);
    }

    private final class Listener
        implements com.resolvix.lib.event.api.Listener<Availability>
    {

        @Override
        public void notify(Availability previous, Availability current) {
            MonitoredConnectionImpl.this.setAvailability(current);
        }
    }

    private void handleSqlConnectionException(SQLException e)
    {
        setAvailability(Availability.DOWN);
        // prompt monitor to test connection
    }

    @Override
    protected void handleSqlException(SQLException e)
    {
        if (isTransientConnectionException(e))
            handleSqlConnectionException(e);
    }

    //
    //  Wrapper
    //

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    //
    //  Connection
    //

    @Override
    public Statement createStatement() throws SQLException {
        return invoke(Statement.class, SQLException.class, connection::createStatement);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql),
            monitor);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return MonitoredCallableStatementImpl.of(
            invoke(CallableStatement.class, SQLException.class, connection::prepareCall, String.class, sql),
            monitor);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return invoke(String.class, SQLException.class, connection::nativeSQL, String.class, sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        invoke(SQLException.class, connection::setAutoCommit, boolean.class, autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return invoke(boolean.class, SQLException.class, connection::getAutoCommit);
    }

    @Override
    public void commit() throws SQLException {
        invoke(SQLException.class, connection::commit);
    }

    @Override
    public void rollback() throws SQLException {
        invoke(SQLException.class, connection::rollback);
    }

    @Override
    public void close() throws SQLException {
        invoke(SQLException.class, connection::close);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return invoke(boolean.class, SQLException.class, connection::isClosed);
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return invoke(DatabaseMetaData.class, SQLException.class, connection::getMetaData);
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        invoke(SQLException.class, connection::setReadOnly, boolean.class, readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return invoke(boolean.class, SQLException.class, connection::isReadOnly);
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        invoke(SQLException.class, connection::setCatalog, String.class, catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return invoke(String.class, SQLException.class, connection::getCatalog);
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        invoke(SQLException.class, connection::setTransactionIsolation, int.class, level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return invoke(int.class, SQLException.class, connection::getTransactionIsolation);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return invoke(SQLWarning.class, SQLException.class, connection::getWarnings);
    }

    @Override
    public void clearWarnings() throws SQLException {
        invoke(SQLException.class, connection::clearWarnings);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return MonitoredStatementImpl.of(
            invoke(Statement.class, SQLException.class, connection::createStatement, int.class, resultSetType, int.class, resultSetConcurrency),
            monitor);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql, int.class, resultSetType, int.class, resultSetConcurrency),
            monitor);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return MonitoredCallableStatementImpl.of(
            invoke(CallableStatement.class, SQLException.class, connection::prepareCall, String.class, sql, int.class, resultSetType, int.class, resultSetConcurrency),
            monitor);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return invoke(Map.class, SQLException.class, connection::getTypeMap);
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        invoke(SQLException.class, connection::setTypeMap, Map.class, map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        invoke(SQLException.class, connection::setHoldability, int.class, holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return invoke(int.class, SQLException.class, connection::getHoldability);
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return invoke(Savepoint.class, SQLException.class, connection::setSavepoint);
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return invoke(Savepoint.class, SQLException.class, connection::setSavepoint, String.class, name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        invoke(SQLException.class, connection::rollback, Savepoint.class, savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        invoke(SQLException.class, connection::releaseSavepoint, Savepoint.class, savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return MonitoredStatementImpl.of(
            invoke(Statement.class, SQLException.class, connection::createStatement, int.class, resultSetType, int.class, resultSetConcurrency, int.class, resultSetHoldability),
            monitor);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql, int.class, resultSetType, int.class, resultSetConcurrency, int.class, resultSetHoldability),
            monitor);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return MonitoredCallableStatementImpl.of(
            invoke(CallableStatement.class, SQLException.class, connection::prepareCall, String.class, sql, int.class, resultSetType, int.class, resultSetConcurrency, int.class, resultSetHoldability),
            monitor);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql, int.class, autoGeneratedKeys),
            monitor);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql, int[].class, columnIndexes),
            monitor);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return MonitoredPreparedStatementImpl.of(
            invoke(PreparedStatement.class, SQLException.class, connection::prepareStatement, String.class, sql, String[].class, columnNames),
            monitor);
    }

    @Override
    public Clob createClob() throws SQLException {
        return invoke(Clob.class, SQLException.class, connection::createClob);
    }

    @Override
    public Blob createBlob() throws SQLException {
        return invoke(Blob.class, SQLException.class, connection::createBlob);
    }

    @Override
    public NClob createNClob() throws SQLException {
        return invoke(NClob.class, SQLException.class, connection::createNClob);
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return invoke(SQLXML.class, SQLException.class, connection::createSQLXML);
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return invoke(boolean.class, SQLException.class, connection::isValid, int.class, timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        invoke(SQLClientInfoException.class, connection::setClientInfo, String.class, name, String.class, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        invoke(SQLClientInfoException.class, connection::setClientInfo, Properties.class, properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return invoke(String.class, SQLException.class, connection::getClientInfo, String.class, name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return invoke(Properties.class, SQLException.class, connection::getClientInfo);
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return invoke(Array.class, SQLException.class, connection::createArrayOf, String.class, typeName, Object[].class, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return invoke(Struct.class, SQLException.class, connection::createStruct, String.class, typeName, Object[].class, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        invoke(SQLException.class, connection::setSchema, String.class, schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return invoke(String.class, SQLException.class, connection::getSchema);
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        invoke(SQLException.class, connection::abort, Executor.class, executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        invoke(SQLException.class, connection::setNetworkTimeout,
            Executor.class, executor, int.class, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return invoke(int.class, SQLException.class, connection::getNetworkTimeout);
    }

    //
    //  MonitoredConnection
    //

    @Override
    public Availability getAvailability() {
        readLock.lock();
        Availability availability = this.availability;
        readLock.unlock();
        return availability;
    }

    private void setAvailability(Availability availability) {
        writeLock.lock();
        try {
            this.availability = availability;
        } finally {
            writeLock.unlock();
        }
    }
}
