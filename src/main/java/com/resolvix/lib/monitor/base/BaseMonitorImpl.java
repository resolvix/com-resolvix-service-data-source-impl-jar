package com.resolvix.lib.monitor.base;

import com.resolvix.lib.event.base.BaseListenerEnabledChangeHandlerImpl;
import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.lib.monitor.api.Probe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public abstract class BaseMonitorImpl<P>
    extends BaseListenerEnabledChangeHandlerImpl<P>
    implements Monitor<P>
{
    private ScheduledExecutorService scheduledExecutorService;

    private List<Probe<P>> probes;

    public BaseMonitorImpl(P initialState, ScheduledExecutorService scheduledExecutorService) {
        super(initialState);
        this.scheduledExecutorService = scheduledExecutorService;
        this.probes = new ArrayList<>();
    }

    @Override
    public synchronized void addProbe(Probe<P> probe) {
        probes.add(probe);
    }

    @Override
    public synchronized List<Probe<P>> getProbes() {
        return Collections.unmodifiableList(probes);
    }

    private synchronized Callable<P> fromProbe(Probe<P> probe) {
        return probe::probe;
    }

    private synchronized void update() {
        try {
            List<Future<P>> futures = scheduledExecutorService.invokeAll(
                probes.stream()
                    .map(this::fromProbe)
                    .collect(Collectors.toList()));
        } catch (InterruptedException e) {

        } finally {

        }
    }

    protected void prompt() {
        scheduledExecutorService.execute(this::update);
    }

    @Override
    public synchronized void removeProbe(Probe<P> probe) {
        probes.remove(probe);
    }
}
