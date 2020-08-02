package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredCallableStatement;
import com.resolvix.service.datasource.api.MonitoredPreparedStatement;
import com.resolvix.service.datasource.api.MonitoredStatement;
import com.resolvix.service.datasource.api.monitor.Availability;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MonitoredConnectionImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private CallableStatement callableStatement;

    @Mock
    private Monitor<Availability> monitor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @Ignore
    public void testNonSqlException() {

    }

    @Test @Ignore
    public void testWarningSqlException() {

    }

    @Test
    public void createStatement()
        throws SQLException
    {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(connection.createStatement())
            .thenReturn(this.statement);
        MonitoredConnectionImpl monitoredConnection
            = MonitoredConnectionImpl.of(connection, monitor);

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        Statement statement = null;
        try {
            statement = monitoredConnection.prepareStatement("SELECT 1 AS X");
        } catch (SQLException e) {
            // do nothing
        }

        assertThat(
            statement,
            allOf(
                notNullValue(),
                instanceOf(Statement.class),
                instanceOf(MonitoredStatement.class)));

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));
    }

    @Test
    public void prepareStatement()
        throws SQLException
    {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(connection.prepareStatement(any(String.class)))
            .thenReturn(this.preparedStatement);
        MonitoredConnectionImpl monitoredConnection
            = MonitoredConnectionImpl.of(connection, monitor);

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = monitoredConnection.prepareStatement("SELECT 1 AS X");
        } catch (SQLException e) {
            // do nothing
        }

        assertThat(
            preparedStatement,
            allOf(
                notNullValue(),
                instanceOf(PreparedStatement.class),
                instanceOf(MonitoredPreparedStatement.class)));

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));
    }

    @Test
    public void prepareCall()
        throws SQLException
    {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(connection.prepareCall(any(String.class)))
            .thenReturn(callableStatement);
        MonitoredConnectionImpl monitoredConnection
            = MonitoredConnectionImpl.of(connection, monitor);

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        CallableStatement callableStatement = null;
        try {
            callableStatement = monitoredConnection.prepareCall("SELECT 1 AS X");
        } catch (SQLException e) {
            // do nothing
        }

        assertThat(
            callableStatement,
            allOf(
                notNullValue(),
                instanceOf(CallableStatement.class),
                instanceOf(MonitoredCallableStatement.class)));

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));
    }

    @Test
    public void prepareCallConnectionExceptionSqlException()
        throws SQLException
    {
        when(monitor.getState())
            .thenReturn(Availability.UP);
        when(connection.prepareCall(any(String.class)))
            .thenThrow(
                new SQLException("Connection exception.", "08000"));
        MonitoredConnectionImpl monitoredConnection
            = MonitoredConnectionImpl.of(connection, monitor);

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        try {
            CallableStatement callableStatement
                = monitoredConnection.prepareCall("SELECT 1 AS X");
        } catch (SQLException e) {
            // do nothing
        }

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.DOWN));
    }
}
