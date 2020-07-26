package com.resolvix.service.datasource.monitor;

import com.resolvix.service.datasource.api.MonitorListener;
import com.resolvix.service.datasource.api.event.AvailabilityChange;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.api.monitor.Monitor;
import com.resolvix.service.datasource.api.monitor.Reliability;

import javax.sql.DataSource;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MonitorImpl<A>
    implements Monitor<A>
{
    private DataSource dataSource;

    private Monitor monitor;

    private volatile A availability;

    private Deque<AvailabilityChange<A>> availabilityChanges = new ArrayDeque<>();

    private List<MonitorListener<A>> listeners = new ArrayList<>();

    public MonitorImpl(
        DataSource dataSource,
        Monitor<A> monitor)
    {
        this.dataSource = dataSource;
        this.monitor = monitor;
    }

    @Override
    public synchronized Availability getAvailability() {
        return null;
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

    @Override
    public synchronized void removeListener(MonitorListener listener) {
        listeners.remove(listener);
    }
}
