package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.monitor.Availability;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class MonitoredPreparedStatementImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private PreparedStatement preparedStatement;

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
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        MonitoredPreparedStatementImpl monitoredPreparedStatement
            = MonitoredPreparedStatementImpl.of(preparedStatement, monitor);

        assertThat(
            monitoredPreparedStatement.executeQuery(),
            sameInstance(resultSet));
    }

    @Test
    public void executeQueryConnectionExceptionSQLException() throws Exception {
        when(monitor.getState()).thenReturn(Availability.UP);
        when(preparedStatement.execute())
            .thenThrow(new SQLException("Connection exception", "08000"));
        MonitoredPreparedStatementImpl monitoredPreparedStatement
            = MonitoredPreparedStatementImpl.of(preparedStatement, monitor);

        try {
            monitoredPreparedStatement.executeQuery();
        } catch (SQLException e) {
            //
        }
    }
}
