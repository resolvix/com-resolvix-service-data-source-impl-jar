package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredConnection;
import com.resolvix.service.datasource.api.monitor.Availability;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class MonitoredDataSourceImplUT {

    @Mock
    private DataSource dataSource;

    @Mock
    private Monitor<Availability> monitor;

    @Mock
    private Connection connection;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void monitoredDataSourceInitialAvailabilityUp() {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, monitor);
        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.UP));

        assertThat(
            monitoredDataSource.isUp(),
            equalTo(true));

        assertThat(
            monitoredDataSource.isDown(),
            equalTo(false));
    }

    @Test
    public void monitoredDataSourceGetConnection() throws Exception {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(dataSource.getConnection())
            .thenReturn(connection);

        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, monitor);

        Connection connection
            = monitoredDataSource.getConnection();

        connection.close();

//        assertTrue(
//            Proxy.isProxyClass(connection.getClass()));

        assertThat(
            connection,
            allOf(
                instanceOf(Connection.class),
                instanceOf(MonitoredConnection.class)));
    }

    @Test
    public void monitoredDataSourceInitialAvailabilityDown() {
        when(monitor.getState())
            .thenReturn(Availability.DOWN);
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, monitor);
        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.DOWN));

        assertThat(
            monitoredDataSource.isUp(),
            equalTo(false));

        assertThat(
            monitoredDataSource.isDown(),
            equalTo(true));
    }

    @Test
    public void monitoredDataSourceIntermittentFailure() throws Exception {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, monitor);
        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.UP));

        when(dataSource.getConnection())
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenReturn(connection)
            .thenReturn(connection)
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"))
            .thenThrow(new SQLException("Connection failure", "08000"));

        for (int i = 1; i <= 4; i++) {
            try {
                monitoredDataSource.getConnection();
            } catch (SQLException e) {
                //
            }
        }

        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.UP));

        for (int i = 1; i <= 2; i++) {
            try {
                monitoredDataSource.getConnection();
            } catch (SQLException e) {
                //
            }
        }

        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.UP));

        for (int i = 1; i <= 4; i++) {
            try {
                monitoredDataSource.getConnection();
            } catch (SQLException e) {
                //
            }
        }

        assertThat(
            monitoredDataSource.getAvailability(),
            equalTo(Availability.DOWN));
    }
}
