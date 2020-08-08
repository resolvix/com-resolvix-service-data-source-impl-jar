package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.base.BaseMonitoredPreparedStatementProxyImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MonitoredPreparedStatementImpl
    extends BaseMonitoredPreparedStatementProxyImpl<PreparedStatement>
{

    private MonitoredPreparedStatementImpl(PreparedStatement statement, Monitor<Availability> monitor) {
        super(statement, monitor);
    }

    public static MonitoredPreparedStatementImpl of(PreparedStatement statement, Monitor<Availability> monitor) {
        return new MonitoredPreparedStatementImpl(statement, monitor);
    }

    @Override
    protected void handleSqlException(SQLException e) {

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
    //  MonitoredPreparedStatement
    //
}
