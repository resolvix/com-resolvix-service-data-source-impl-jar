package com.resolvix.lib.monitor.base;

import com.resolvix.lib.monitor.api.Listener;
import com.resolvix.lib.monitor.api.Probe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ScheduledExecutorService;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class BaseMonitorImplUT {

    private enum LocalProperty {

        VALUE;
    }

    private static class LocalMonitor
        extends BaseMonitorImpl<LocalProperty>
    {

        public LocalMonitor(ScheduledExecutorService scheduledExecutorService) {
            super(scheduledExecutorService);
        }
    }

    private LocalMonitor localMonitor;

    @Mock
    private ScheduledExecutorService scheduledExecutorService;

    @Mock
    private Listener<LocalProperty> listener;

    @Mock
    private Probe<LocalProperty> probe;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.localMonitor = new LocalMonitor(scheduledExecutorService);
    }

    @Test
    public void addAndRemoveListener() {
        localMonitor.addListener(listener);
        assertThat(
            localMonitor.getListeners(),
            contains(listener));
        localMonitor.removeListener(listener);
        assertThat(
            localMonitor.getListeners(),
            empty());
    }

    public void addAndRemoveProbe() {
        localMonitor.addProbe(probe);
        assertThat(
            localMonitor.getProbes(),
            contains(probe));
        localMonitor.removeProbe(probe);
        assertThat(
            localMonitor.getProbes(),
            empty());
    }
}
