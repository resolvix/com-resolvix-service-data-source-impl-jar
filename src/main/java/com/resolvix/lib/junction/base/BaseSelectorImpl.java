package com.resolvix.lib.junction.base;

import com.resolvix.service.datasource.api.SelectorListener;
import com.resolvix.service.datasource.api.event.StateChange;
import com.resolvix.service.datasource.api.selector.Selector;
import com.resolvix.service.datasource.event.StateChangeImpl;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.*;

public abstract class BaseSelectorImpl<S>
    implements Selector<S>
{
    private DataSource dataSource;

    private volatile S state;

    private Deque<StateChange<S>> stateChanges = new ArrayDeque<>();

    private List<SelectorListener<S>> listeners = new ArrayList<>();

    public BaseSelectorImpl(
        DataSource dataSource,
        S initialState
    ) {
        this.dataSource = dataSource;
        this.state = initialState;
    }

    @Override
    public synchronized S getState() {
        return state;
    }

    protected void setState(S state) {
        StateChange<S> stateChange = StateChangeImpl.of(
            this.state, state, Instant.now());
        synchronized (this) {
            this.stateChanges.add(stateChange);
            this.state = state;
        }
        for (SelectorListener<S> listener : this.listeners)
            listener.updateState(state);
    }

    @Override
    public synchronized List<StateChange<S>> getRecentChangeHistory() {
        return new ArrayList<>(stateChanges);
    }

    @Override
    public synchronized void addListener(SelectorListener<S> listener) {
        listeners.add(listener);
    }

    protected synchronized List<SelectorListener<S>> getListeners() {
        return Collections.unmodifiableList(listeners);
    }

    @Override
    public synchronized void removeListener(SelectorListener<S> listener) {
        listeners.remove(listener);
    }
}
