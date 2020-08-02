package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredStatement;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.base.proxy.BaseMonitoredStatementProxyImpl;

import java.sql.SQLException;
import java.sql.Statement;

public class MonitoredStatementImpl
    extends BaseMonitoredStatementProxyImpl<Statement>
    implements MonitoredStatement
{

    private MonitoredStatementImpl(Statement statement, Monitor<Availability> monitor) {
        super(statement, monitor);
    }

    public static MonitoredStatementImpl of(Statement statement, Monitor<Availability> monitor) {
        return new MonitoredStatementImpl(statement, monitor);
    }

    @Override
    protected void handleSqlException(SQLException e) {

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
