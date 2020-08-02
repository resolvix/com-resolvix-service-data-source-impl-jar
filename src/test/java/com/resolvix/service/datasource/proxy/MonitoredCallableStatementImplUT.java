package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.MonitoredCallableStatementImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class MonitoredCallableStatementImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CallableStatement callableStatement;

    @Mock
    private Monitor<Availability> monitor;

    @Mock
    private ResultSet resultSet;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeQuery() throws Exception {
        when(monitor.getState()).thenReturn(Availability.UP);
        when(callableStatement.executeUpdate()).thenReturn(1);
        MonitoredCallableStatementImpl monitoredCallableStatement
            = MonitoredCallableStatementImpl.of(callableStatement, monitor);

        assertThat(
            monitoredCallableStatement.executeUpdate(),
            equalTo(1));
    }

    @Test
    public void executeQueryConnectionExceptionSQLException() throws Exception {
        when(monitor.getState()).thenReturn(Availability.UP);
        when(callableStatement.executeUpdate())
            .thenThrow(new SQLException("Connection exception", "08000"));
        MonitoredCallableStatementImpl monitoredCallableStatement
            = MonitoredCallableStatementImpl.of(callableStatement, monitor);

        try {
            monitoredCallableStatement.executeUpdate();
        } catch (SQLException e) {
            //
        }
    }
}
