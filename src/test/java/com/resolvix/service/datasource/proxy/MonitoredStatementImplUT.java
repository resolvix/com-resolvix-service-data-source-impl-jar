package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.monitor.Availability;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MonitoredStatementImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Statement statement;

    @Mock
    private Monitor<Availability> monitor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        when(monitor.getState()).thenReturn(Availability.UP);
        when(statement.execute(any(String.class))).thenReturn(true);
        MonitoredStatementImpl monitoredStatement
            = MonitoredStatementImpl.of(statement, monitor);

        assertThat(
            monitoredStatement.execute("SELECT 1 AS A;"),
            equalTo(true));
    }

    @Test
    public void executeConnectionExceptionSQLException() throws Exception {
        when(monitor.getState()).thenReturn(Availability.UP);
        when(statement.execute(any(String.class)))
            .thenThrow(new SQLException("Connection exception", "08000"));
        MonitoredStatementImpl monitoredStatement
            = MonitoredStatementImpl.of(statement, monitor);

        try {
            monitoredStatement.execute("SELECT 1 AS A;");
        } catch (SQLException e) {
            //
        }
    }
}
