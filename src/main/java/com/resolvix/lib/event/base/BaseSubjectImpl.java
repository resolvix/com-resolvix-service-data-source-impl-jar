package com.resolvix.lib.event.base;

import com.google.common.collect.EvictingQueue;
import com.resolvix.lib.event.api.Change;
import com.resolvix.lib.event.api.Listener;
import com.resolvix.lib.event.api.RecentChangeHistory;
import com.resolvix.lib.event.api.Subject;
import com.resolvix.service.datasource.event.ChangeImpl;

import java.lang.ref.WeakReference;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static com.resolvix.lib.reference.WeakReferenceUtils.compact;
import static com.resolvix.lib.reference.WeakReferenceUtils.find;

public class BaseSubjectImpl<P>
    implements Subject<P>, RecentChangeHistory<P>
{
    private static final int DEFAULT_RECENT_CHANGE_HISTORY_LENGTH = 100;

    private volatile P state;

    private final Queue<Change<P>> changes;

    private List<WeakReference<Listener<P>>> listeners;

    public BaseSubjectImpl(P initialState, int recentHistory) {
        this.state = initialState;
        this.changes = EvictingQueue.create(recentHistory);
        this.listeners = new ArrayList<>();
    }

    public BaseSubjectImpl(P initialState) {
        this(initialState, DEFAULT_RECENT_CHANGE_HISTORY_LENGTH);
    }

    @Override
    public synchronized P getState() {
        return state;
    }

    protected synchronized void setState(P state) {
        Change<P> change = ChangeImpl.of(this.state, state, Instant.now());
        this.changes.add(change);
        this.state = state;
    }

    @Override
    public List<Change<P>> getRecentChangeHistory() {
        return Collections.unmodifiableList(
            new ArrayList<>(changes));
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
}
