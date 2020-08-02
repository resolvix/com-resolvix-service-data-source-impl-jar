package com.resolvix.service.datasource.base.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredStatement;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.MonitoredResultSetImpl;

import java.sql.*;

public abstract class BaseMonitoredStatementProxyImpl<S extends Statement>
    extends BaseStaticProxyImpl
    implements MonitoredStatement, Statement
{
    protected final S statement;

    protected final Monitor<Availability> monitor;

    protected Availability availability;

    protected BaseMonitoredStatementProxyImpl(S statement, Monitor<Availability> monitor) {
        this.statement = statement;
        this.monitor = monitor;
        this.availability = monitor.getState();
    }

    //
    //  Statement
    //

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return invoke(ResultSet.class, SQLException.class, statement::executeQuery, String.class, sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return invoke(int.class, SQLException.class, statement::executeUpdate, String.class, sql);
    }

    @Override
    public void close() throws SQLException {
        invoke(SQLException.class, statement::close);
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getMaxFieldSize);
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        invoke(SQLException.class, statement::setMaxFieldSize, int.class, max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getMaxRows);
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        invoke(SQLException.class, statement::setMaxRows, int.class, max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        invoke(SQLException.class, statement::setEscapeProcessing, boolean.class, enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getQueryTimeout);
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        invoke(SQLException.class, statement::setQueryTimeout, int.class, seconds);
    }

    @Override
    public void cancel() throws SQLException {
        invoke(SQLException.class, statement::cancel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return invoke(SQLWarning.class, SQLException.class, statement::getWarnings);
    }

    @Override
    public void clearWarnings() throws SQLException {
        invoke(SQLException.class, statement::clearWarnings);
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        invoke(SQLException.class, statement::setCursorName, String.class, name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::execute, String.class, sql);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return MonitoredResultSetImpl.of(
            invoke(ResultSet.class, SQLException.class, statement::getResultSet),
            monitor);
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getUpdateCount);
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::getMoreResults);
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        invoke(SQLException.class, statement::setFetchDirection, int.class, direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getFetchDirection);
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        invoke(SQLException.class, statement::setFetchSize, int.class, rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getFetchSize);
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getResultSetConcurrency);
    }

    @Override
    public int getResultSetType() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getResultSetType);
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        invoke(SQLException.class, statement::addBatch, String.class, sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        invoke(SQLException.class, statement::clearBatch);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return invoke(int[].class, SQLException.class, statement::executeBatch);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return invoke(Connection.class, SQLException.class, statement::getConnection);
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::getMoreResults, int.class, current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return invoke(ResultSet.class, SQLException.class, statement::getGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return invoke(int.class, SQLException.class, statement::executeUpdate, String.class, sql, int.class, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return invoke(int.class, SQLException.class, statement::executeUpdate, String.class, sql, int[].class, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return invoke(int.class, SQLException.class, statement::executeUpdate, String.class, sql, String[].class, columnNames);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::execute, String.class, sql, int.class, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::execute, String.class, sql, int[].class, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::execute, String.class, sql, String[].class, columnNames);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return invoke(int.class, SQLException.class, statement::getResultSetHoldability);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::isClosed);
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        invoke(SQLException.class, statement::setPoolable, boolean.class, poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::isPoolable);
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        invoke(SQLException.class, statement::closeOnCompletion);
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::isCloseOnCompletion);
    }

    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        invoke(SQLException.class, statement::setLargeMaxRows, long.class, max);
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        return invoke(long.class, SQLException.class, statement::getLargeUpdateCount);
    }

    @Override
    public long getLargeMaxRows() throws SQLException {
        return invoke(long.class, SQLException.class, statement::getLargeMaxRows);
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        return invoke(long[].class, SQLException.class, statement::executeLargeBatch);
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return invoke(long.class, SQLException.class, statement::executeLargeUpdate, String.class, sql);
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return invoke(long.class, SQLException.class, statement::executeLargeUpdate, String.class, sql, int.class, autoGeneratedKeys);
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return invoke(long.class, SQLException.class, statement::executeLargeUpdate, String.class, sql, int[].class, columnIndexes);
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return invoke(long.class, SQLException.class, statement::executeLargeUpdate, String.class, sql, String[].class, columnNames);
    }
}
