package com.resolvix.lib.monitor.base;

import com.resolvix.lib.monitor.api.Listener;
import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.lib.monitor.api.Probe;

import javax.validation.constraints.NotNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public abstract class BaseMonitorImpl<P>
    implements Monitor<P>
{
    private ScheduledExecutorService scheduledExecutorService;

    private List<Probe<P>> probes;

    private List<WeakReference<Listener<P>>> listeners;

    public BaseMonitorImpl(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.listeners = new ArrayList<>();
        this.probes = new ArrayList<>();
    }

    private <T> WeakReference<T> find(Iterable<WeakReference<T>> iterable, @NotNull T t) {
        Iterator<WeakReference<T>> it = iterable.iterator();
        while (it.hasNext()) {
            WeakReference<T> weakReference = it.next();
            if (t.equals(weakReference.get()))
                return weakReference;
        }
        return null;
    }

    @Override
    public void addListener(Listener<P> listener) {
        if (find(listeners, listener) == null)
            listeners.add(
                new WeakReference<>(listener));
    }

    @Override
    public List<Listener<P>> getListeners() {
        return Collections.unmodifiableList(
            listeners.stream()
                .map(WeakReference::get)
                .collect(Collectors.toList()));
    }

    @Override
    public void removeListener(Listener<P> listener) {
        WeakReference<Listener<P>> weakReference
            = find(listeners, listener);
        if (weakReference != null)
            listeners.remove(weakReference);
    }

    @Override
    public void addProbe(Probe<P> probe) {
        probes.add(probe);
    }

    @Override
    public List<Probe<P>> getProbes() {
        return Collections.unmodifiableList(probes);
    }

    private Callable<P> fromProbe(Probe<P> probe) {
        return probe::probe;
    }

    private void update() {
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
    public void removeProbe(Probe<P> probe) {
        probes.remove(probe);
    }
}
