package com.resolvix.service.datasource;

import com.resolvix.service.datasource.api.monitor.Availability;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class MonitoredDataSourceImplUT {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void monitoredDataSourceInitialAvailabilityUp() {
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, Availability.UP);
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
    public void monitoredDataSourceInitialAvailabilityDown() {
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, Availability.DOWN);
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
        MonitoredDataSourceImpl monitoredDataSource
            = MonitoredDataSourceImpl.of(dataSource, Availability.UP);
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

        for (int i = 1; i <= 2; i++) {
            try {
                monitoredDataSource.getConnection();
            } catch (SQLException e) {
                //
            }
        }

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
