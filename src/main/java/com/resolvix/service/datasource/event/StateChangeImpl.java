package com.resolvix.service.datasource.event;

import com.resolvix.service.datasource.api.event.StateChange;

import java.time.Instant;

public class StateChangeImpl<S>
    implements StateChange<S>
{

    private S previousState;

    private S state;

    private Instant instant;

    private StateChangeImpl(S previousState, S state, Instant instant) {
        this.previousState = previousState;
        this.state = state;
        this.instant = instant;
    }

    public static <S> StateChange<S> of(S previousState, S state, Instant instant) {
        return new StateChangeImpl<>(previousState, state, instant);
    }

    @Override
    public S getPreviousState() {
        return previousState;
    }

    @Override
    public S getState() {
        return state;
    }

    @Override
    public Instant getInstant() {
        return instant;
    }
}
