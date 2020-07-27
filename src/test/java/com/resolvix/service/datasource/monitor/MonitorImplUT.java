package com.resolvix.service.datasource.monitor;

import com.resolvix.service.datasource.api.MonitorListener;
import com.resolvix.service.datasource.api.monitor.Monitor;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.util.List;

public class MonitorImplUT {

    private enum Availability
    {
        UP,

        DOWN;
    }

    private class Listener
        implements MonitorListener<MonitorImplUT.Availability>
    {

        @Override
        public void updateAvailability(MonitorImplUT.Availability availability) {
            MonitorImplUT.this.availability = availability;
        }
    }

    private class LocalMonitorImpl
        extends MonitorImpl<MonitorImplUT.Availability>
    {

        public LocalMonitorImpl(
            DataSource dataSource,
            MonitorImplUT.Availability initialAvailability) {
            super(dataSource, initialAvailability);
        }

        public void update(MonitorImplUT.Availability availability) {
            setAvailability(availability);
        }

        public List<MonitorListener<MonitorImplUT.Availability>> getListeners() {
            return super.getListeners();
        }
    }

    @Mock
    private DataSource dataSource;

    private LocalMonitorImpl monitor;

    private Availability availability;

    private Listener listener = new Listener();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        monitor = new LocalMonitorImpl(dataSource, Availability.UP);
    }

    @Test
    public void monitorLifecycle() {
        Assert.assertThat(
            monitor.getAvailability(),
            Matchers.equalTo(Availability.UP));

        monitor.addListener(listener);
        Assert.assertThat(
            monitor.getListeners(),
            Matchers.contains(listener));

        monitor.update(Availability.DOWN);
        Assert.assertThat(
            monitor.getAvailability(),
            Matchers.equalTo(Availability.DOWN));

        monitor.removeListener(listener);
        Assert.assertThat(
            monitor.getListeners(),
            Matchers.empty());
    }
}
