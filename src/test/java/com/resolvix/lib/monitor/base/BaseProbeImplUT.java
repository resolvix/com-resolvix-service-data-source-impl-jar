package com.resolvix.lib.monitor.base;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BaseProbeImplUT {

    private enum LocalProperty {

        VALUE;

    }

    private static class LocalProbeImpl
        extends BaseProbeImpl<LocalProperty>
    {

        @Override
        public LocalProperty probe() {
            return LocalProperty.VALUE;
        }
    }

    LocalProbeImpl probe;

    @Before
    public void before() {
        this.probe = new LocalProbeImpl();
    }

    @Test
    public void test() {
        assertThat(
            probe.probe(),
            equalTo(LocalProperty.VALUE));
    }
}
