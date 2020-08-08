package com.resolvix.service.datasource.base;

import com.resolvix.lib.event.api.Listener;
import com.resolvix.lib.junction.api.Junction;
import com.resolvix.lib.junction.api.Selector;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseSwitchedDataSourceImplUT {

    private enum State {

        ONLINE,

        WARNING,

        OFFLINE,

        NOT_AVAILABLE;

    }

    private class OnlineOfflineSwitchedDataSourceImpl
        extends BaseSwitchedDataSourceImpl<State, Selector<State>, DataSource>
        implements Junction<State, Selector<State>, DataSource>
    {
        private DataSource onlineDataSource;

        private DataSource offlineDataSource;

        protected OnlineOfflineSwitchedDataSourceImpl(
            DataSource onlineDataSource, DataSource offlineDataSource, State initialState, Selector<State> selector) {
            super(initialState, selector);
            this.onlineDataSource = onlineDataSource;
            this.offlineDataSource = offlineDataSource;
        }

        private DataSource getDataSource() throws SQLException {
            State state = getState();
            switch (state) {
                case ONLINE:
                case WARNING:
                    return onlineDataSource;

                case OFFLINE:
                    return offlineDataSource;

                case NOT_AVAILABLE:
                    // Connection not available
                    throw new SQLException();

                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public Connection getConnection() throws SQLException {
            return getDataSource().getConnection();
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return getDataSource().getConnection(username, password);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            throw new UnsupportedOperationException();
        }
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private DataSource onlineDataSource;

    @Mock
    private Connection onlineConnection;

    @Mock
    private DataSource offlineDataSource;

    @Mock
    private Connection offlineConnection;

    @Mock
    private Selector<State> selector;

    private Listener<State> listener;

    private OnlineOfflineSwitchedDataSourceImpl onlineOfflineSwitchedDataSource;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        //
        Mockito.when(selector.getState())
            .thenReturn(State.NOT_AVAILABLE);
        Mockito.doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                Listener<State> listener = invocationOnMock.getArgument(0);
                BaseSwitchedDataSourceImplUT.this.listener = listener;
                return null;
            }
        }).when(selector)
            .addListener(ArgumentMatchers.any(Listener.class));

        //
        Mockito.when(onlineDataSource.getConnection())
            .thenReturn(onlineConnection);

        //
        Mockito.when(offlineDataSource.getConnection())
            .thenReturn(offlineConnection);

        onlineOfflineSwitchedDataSource = new OnlineOfflineSwitchedDataSourceImpl(
            onlineDataSource, offlineDataSource, State.NOT_AVAILABLE, selector);
    }

    @Test
    public void testInitialState() throws Exception {
        expectedException.expect(SQLException.class);
        onlineOfflineSwitchedDataSource.getConnection();
    }

    @Test
    public void testOnlineState() throws Exception {
        listener.notify(State.ONLINE);
        Connection connection = onlineOfflineSwitchedDataSource.getConnection();
        Assert.assertThat(connection, Matchers.sameInstance(onlineConnection));
    }

    @Test
    public void testWarningState() throws Exception {
        listener.notify(State.WARNING);
        Connection connection = onlineOfflineSwitchedDataSource.getConnection();
        Assert.assertThat(connection, Matchers.sameInstance(onlineConnection));
    }

    @Test
    public void testOfflineState() throws Exception {
        listener.notify(State.OFFLINE);
        Connection connection = onlineOfflineSwitchedDataSource.getConnection();
        Assert.assertThat(connection, Matchers.sameInstance(offlineConnection));
    }
}
