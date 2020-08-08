package com.resolvix.service.datasource;

import com.resolvix.lib.junction.api.Selector;
import com.resolvix.service.datasource.api.MonitoredDataSource;
import com.resolvix.service.datasource.api.OnlineOfflineSwitchedDataSource;
import com.resolvix.service.datasource.api.State;
import com.resolvix.service.datasource.base.BaseSwitchedDataSourceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class OnlineOfflineSwitchedDataSourceImpl<P extends State, S extends Selector<P>>
    extends BaseSwitchedDataSourceImpl<P, S, MonitoredDataSource<?, ?, ?>>
    implements OnlineOfflineSwitchedDataSource<P, S>
{
    private MonitoredDataSource<?, ?, ?> monitoredOnlineDataSource;

    private MonitoredDataSource<?, ?, ?> monitoredOfflineDataSource;

    public OnlineOfflineSwitchedDataSourceImpl(
        MonitoredDataSource<?, ?, ?> monitoredOnlineDataSource,
        MonitoredDataSource<?, ?, ?> monitoredOfflineDataSource,
        P initialState, S selector) {
        super(initialState, selector);
        this.monitoredOnlineDataSource = monitoredOnlineDataSource;
        this.monitoredOfflineDataSource = monitoredOfflineDataSource;
    }

    protected MonitoredDataSource<?, ?, ?> getMonitoredDataSource() throws SQLException {
        State state = getState();
        if (state.isOnline())
            return monitoredOnlineDataSource;

        if (state.isOffline())
            return monitoredOfflineDataSource;

        if (state.isNotAvailable())
            // Connection not available
            throw new SQLException();

        throw new IllegalStateException();
    }

    //
    //  DataSource
    //

    @Override
    public Connection getConnection() throws SQLException {
        return getMonitoredDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getMonitoredDataSource().getConnection(username, password);
    }

    //
    //  Wrapper
    //

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        //MonitoredDataSource<?, ?, ?> monitoredDataSource = getMonitoredDataSource();
        //Class<?> clazz = monitoredDataSource.getClass();
        //while (iface.)

        return (T) getMonitoredDataSource();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isAssignableFrom(this.getClass()));
    }
}
