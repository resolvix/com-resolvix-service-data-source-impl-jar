package com.resolvix.lib.junction.base;

import com.resolvix.lib.event.base.BaseListenerEnabledChangeHandlerImpl;
import com.resolvix.lib.junction.api.Junction;
import com.resolvix.lib.junction.api.Selector;

public abstract class BaseJunctionImpl<P, S extends Selector<P>, R>
    extends BaseListenerEnabledChangeHandlerImpl<P>
    implements Junction<P, S, R>
{
    private S selector;

    public BaseJunctionImpl(P initialState, S selector) {
        super(initialState);
        this.selector = selector;
    }

    @Override
    public S getSelector() {
        return selector;
    }
}
