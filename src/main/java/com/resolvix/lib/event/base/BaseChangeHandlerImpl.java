package com.resolvix.lib.event.base;

import com.resolvix.lib.event.api.Change;
import com.resolvix.lib.event.api.RecentChangeHistory;

import java.util.List;

public class BaseChangeHandlerImpl<P>
    implements RecentChangeHistory<P>
{

    private volatile P state;

    protected BaseChangeHandlerImpl(P initialState) {
        this.state = initialState;
    }

    public synchronized P getState() {
        return state;
    }

    protected synchronized void setState(P state) {
        this.state = state;
    }

    @Override
    public List<Change<P>> getRecentChangeHistory() {
        return null;
    }
}
