package com.resolvix.service.datasource.monitor;

import com.resolvix.service.datasource.api.MonitorListener;
import com.resolvix.service.datasource.api.event.AvailabilityChange;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.api.monitor.Monitor;
import com.resolvix.service.datasource.api.monitor.Reliability;
import com.resolvix.service.datasource.event.AvailabilityChangeImpl;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.*;

public class MonitorImpl<A>
    implements Monitor<A>
{
    private DataSource dataSource;

    private volatile A availability;

    private Deque<AvailabilityChange<A>> availabilityChanges = new ArrayDeque<>();

    private List<MonitorListener<A>> listeners = new ArrayList<>();

    public MonitorImpl(
        DataSource dataSource,
        A initialAvailability) {
        this.dataSource = dataSource;
        this.availability = initialAvailability;
    }

    @Override
    public synchronized A getAvailability() {
        return availability;
    }

    protected void setAvailability(A availability) {
        AvailabilityChange<A> availabilityChange = AvailabilityChangeImpl.of(
            this.availability, availability, Instant.now());
        synchronized (this) {
            this.availabilityChanges.add(availabilityChange);
            this.availability = availability;
        }
        for (MonitorListener<A> listener : this.listeners)
            listener.updateAvailability(availability);
    }

    @Override
    public synchronized Reliability getReliability() {
        return null;
    }

    @Override
    public synchronized List<AvailabilityChange<A>> getRecentChangeHistory() {
        return new ArrayList<>(availabilityChanges);
    }

    @Override
    public synchronized void addListener(MonitorListener listener) {
        listeners.add(listener);
    }

    protected synchronized List<MonitorListener<A>> getListeners() {
        return Collections.unmodifiableList(listeners);
    }

    @Override
    public synchronized void removeListener(MonitorListener listener) {
        listeners.remove(listener);
    }
}
