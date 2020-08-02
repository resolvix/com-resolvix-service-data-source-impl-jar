package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.lib.monitor.base.DynamicProxyUtils;
import com.resolvix.service.datasource.api.MonitoredConnection;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.MonitoredConnectionInvocationHandlerImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MonitoredConnectionInvocationHandlerImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Connection connection;

    @Mock
    private Monitor<Availability> monitor;


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    @Test @Ignore
    public void testNonSqlException() {

    }

    @Test @Ignore
    public void testWarningSqlException() {

    }

    @Test
    public void testPrepareCallConnectionExceptionSqlException()
        throws SQLException
    {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(connection.prepareCall(any(String.class)))
            .thenThrow(
                new SQLException("Connection exception.", "08000"));
        MonitoredConnectionInvocationHandlerImpl monitoredConnectionInvocationHandler
            = MonitoredConnectionInvocationHandlerImpl.of(connection, monitor);

        MonitoredConnection monitoredConnection
            = DynamicProxyUtils.createProxy(monitoredConnectionInvocationHandler,
                Connection.class, MonitoredConnection.class);

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        try {
            monitoredConnection.prepareCall("SELECT 1 AS X");
        } catch (SQLException e) {
            // do nothing
        }

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.DOWN));
    }
}
