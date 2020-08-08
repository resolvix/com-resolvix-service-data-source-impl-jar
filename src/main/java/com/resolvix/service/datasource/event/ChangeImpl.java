package com.resolvix.service.datasource.event;

import com.resolvix.lib.event.api.Change;

import java.time.Instant;

public class ChangeImpl<P>
    implements Change<P>
{
    private final P previousState;

    private final P state;

    private final Instant instant;

    private ChangeImpl(P previousState, P state, Instant instant) {
        this.previousState = previousState;
        this.state = state;
        this.instant = instant;
    }

    public static <P> Change<P> of(P previousState, P state, Instant instant) {
        return new ChangeImpl<>(previousState, state, instant);
    }

    @Override
    public P getPreviousState() {
        return previousState;
    }

    @Override
    public P getState() {
        return state;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }
}
