package com.resolvix.lib.monitor.base;

import com.resolvix.lib.monitor.api.Listener;
import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.lib.monitor.api.Probe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

import static com.resolvix.lib.monitor.base.WeakReferenceUtils.compact;
import static com.resolvix.lib.monitor.base.WeakReferenceUtils.find;

public abstract class BaseMonitorImpl<P>
    implements Monitor<P>
{
    private ScheduledExecutorService scheduledExecutorService;

    private volatile P state;

    private List<Probe<P>> probes;

    private List<WeakReference<Listener<P>>> listeners;

    public BaseMonitorImpl(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.listeners = new ArrayList<>();
        this.probes = new ArrayList<>();
    }

    public synchronized P getState() {
        return state;
    }

    @Override
    public synchronized void addListener(Listener<P> listener) {
        if (find(listeners, listener) == null) {
            listeners = compact(listeners);
            listeners.add(new WeakReference<>(listener));
        }
    }

    @Override
    public synchronized List<Listener<P>> getListeners() {
        return Collections.unmodifiableList(
            listeners.stream()
                .map(WeakReference::get)
                .collect(Collectors.toList()));
    }

    @Override
    public synchronized void removeListener(Listener<P> listener) {
        WeakReference<Listener<P>> weakReference
            = find(listeners, listener);
        if (weakReference != null)
            listeners.remove(weakReference);
        listeners = compact(listeners);
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
