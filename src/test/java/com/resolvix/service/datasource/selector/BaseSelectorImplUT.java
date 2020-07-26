package com.resolvix.service.datasource.selector;

import com.resolvix.service.datasource.api.SelectorListener;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.List;

public class BaseSelectorImplUT {

    private enum State {

        ONLINE,

        WARNING,

        OFFLINE,

        NOT_AVAILABLE;

    }

    private class SelectorImpl
        extends BaseSelectorImpl<State>
    {

        public SelectorImpl(DataSource dataSource) {
            super(dataSource, State.NOT_AVAILABLE);
        }

        public void update(State state) {
            setState(state);
        }

        public List<SelectorListener<State>> getListeners() {
            return super.getListeners();
        }
    }

    @Mock
    private DataSource dataSource;

    @Mock
    private SelectorListener<State> listener;

    private SelectorImpl selector;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        selector = new SelectorImpl(dataSource);
    }

    @Test
    public void selectorLifecycle() {
        Assert.assertThat(
            selector.getState(),
            Matchers.equalTo(State.NOT_AVAILABLE));

        selector.addListener(listener);
        Assert.assertThat(
            selector.getListeners(),
            Matchers.contains(listener));

        selector.update(State.ONLINE);
        Assert.assertThat(
            selector.getState(),
            Matchers.equalTo(State.ONLINE));
        Mockito.verify(listener)
            .updateState(State.ONLINE);

        Assert.assertThat(
            selector.getRecentChangeHistory(),
            Matchers.hasItem(
                Matchers.allOf(
                    Matchers.hasProperty("previousState", Matchers.equalTo(State.NOT_AVAILABLE)),
                    Matchers.hasProperty("state", Matchers.equalTo(State.ONLINE)),
                    Matchers.hasProperty("instant", Matchers.instanceOf(Instant.class)))));

        selector.removeListener(listener);
        Assert.assertThat(
            selector.getListeners(),
            Matchers.empty());
    }
}
