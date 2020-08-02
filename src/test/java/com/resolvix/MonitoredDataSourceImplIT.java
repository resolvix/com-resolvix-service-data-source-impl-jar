package com.resolvix;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredConnection;
import com.resolvix.service.datasource.api.MonitoredPreparedStatement;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.MonitoredDataSourceImpl;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class MonitoredDataSourceImplIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredDataSourceImplIT.class);

    private MonitoredDataSourceImpl monitoredDataSource;

    @Mock
    private Monitor<Availability> monitor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(monitor.getState())
            .thenReturn(Availability.UP);

        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:~/test");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("sa");

        this.monitoredDataSource
            = MonitoredDataSourceImpl.of(jdbcDataSource, monitor);
    }

    @After
    public void after() {
        monitoredDataSource = null;
    }

    @Test
    public void basicQuery() throws Exception {
        MonitoredConnection monitoredConnection
            = (MonitoredConnection) monitoredDataSource.getConnection();

        assertThat(
            monitoredConnection.getAvailability(),
            equalTo(Availability.UP));

        MonitoredPreparedStatement monitoredPreparedStatement
            = (MonitoredPreparedStatement) monitoredConnection.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.TABLES");

        boolean result = monitoredPreparedStatement.execute();
        if (result) {
            ResultSet resultSet = monitoredPreparedStatement.getResultSet();
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                LOGGER.debug("tableName is {}", tableName);
            }
            resultSet.close();
        }

        monitoredConnection.close();
    }
}
