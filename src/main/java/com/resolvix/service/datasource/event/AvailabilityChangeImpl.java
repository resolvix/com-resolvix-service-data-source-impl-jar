package com.resolvix.service.datasource.event;

import com.resolvix.service.datasource.api.event.AvailabilityChange;

import java.time.Instant;

public class AvailabilityChangeImpl<A>
    implements AvailabilityChange<A> {

    private A previousAvailability;

    private A availability;

    private Instant instant;

    private AvailabilityChangeImpl(A previousAvailability, A availability, Instant instant) {
        this.previousAvailability = previousAvailability;
        this.availability = availability;
        this.instant = instant;
    }

    public static <A> AvailabilityChange<A> of(A previousAvailability, A availability, Instant instant) {
        return new AvailabilityChangeImpl<>(previousAvailability, availability, instant);
    }

    @Override
    public A getPreviousAvailability() {
        return null;
    }

    @Override
    public A getAvailability() {
        return null;
    }

    @Override
    public Instant getInstant() {
        return null;
    }
}
