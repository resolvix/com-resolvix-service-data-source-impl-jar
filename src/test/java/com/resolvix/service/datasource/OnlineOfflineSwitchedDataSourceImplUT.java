package com.resolvix.service.datasource;

import com.resolvix.lib.event.api.Listener;
import com.resolvix.lib.junction.api.Selector;
import com.resolvix.service.datasource.api.MonitoredDataSource;
import com.resolvix.service.datasource.api.OnlineOfflineSwitchedDataSource;
import com.resolvix.service.datasource.api.selector.State;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class OnlineOfflineSwitchedDataSourceImplUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Connection onlineConnection;

    @Mock
    private MonitoredDataSource<?, ?, ?> monitoredOnlineDataSource;

    @Mock
    private Connection offlineConnection;

    @Mock
    private MonitoredDataSource<?, ?, ?> monitoredOfflineDataSource;

    @Mock
    private Selector<State> selector;

    private OnlineOfflineSwitchedDataSource<State, Selector<State>> onlineOfflineSwitchedDataSource;

    private Listener<State> listener;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        //
        //  getConnection
        //

        when(monitoredOnlineDataSource.getConnection())
            .thenReturn(onlineConnection);

        when(monitoredOfflineDataSource.getConnection())
            .thenReturn(offlineConnection);

        //
        //  getConnection(String, String)
        //

        when(monitoredOnlineDataSource.getConnection(any(String.class), any(String.class)))
            .thenReturn(onlineConnection);

        when(monitoredOfflineDataSource.getConnection(any(String.class), any(String.class)))
            .thenReturn(offlineConnection);

        //
        //
        //

        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                OnlineOfflineSwitchedDataSourceImplUT.this.listener
                    = (Listener<State>) invocationOnMock.getArgument(0);
                return null;
            }
        }).when(selector).addListener(any(Listener.class));
        this.onlineOfflineSwitchedDataSource = new OnlineOfflineSwitchedDataSourceImpl<>(
            monitoredOnlineDataSource, monitoredOfflineDataSource, State.NOT_AVAILABLE, selector);
    }

    @Test
    public void getConnectionWhenOnline() throws Exception {
        listener.notify(State.OFFLINE, State.ONLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(onlineConnection));

    }

    @Test
    public void getConnectionWithUserNamePasswordWhenOnline() throws Exception {
        listener.notify(State.OFFLINE, State.ONLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection("<userName>", "<password>"),
            sameInstance(onlineConnection));
    }

    @Test
    public void getConnectionWhenOffline() throws Exception {
        listener.notify(State.OFFLINE, State.OFFLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(offlineConnection));

    }

    @Test
    public void getConnectionWithUserNamePasswordWhenOffline() throws Exception {
        listener.notify(State.ONLINE, State.OFFLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection("<userName>", "<password>"),
            sameInstance(offlineConnection));
    }

    @Test
    public void getConnectionWhenNotAvailable() throws Exception {
        expectedException.expect(SQLException.class);
        onlineOfflineSwitchedDataSource.getConnection();
    }

    @Test
    public void getConnectionWithUserNamePasswordWhenNotAvailable() throws Exception {
        expectedException.expect(SQLException.class);
        onlineOfflineSwitchedDataSource.getConnection("<userName>", "<password>");
    }

    @Test
    public void getConnectionLifecycle() throws Exception {
        try {
            onlineOfflineSwitchedDataSource.getConnection();
            throw new IllegalStateException();
        } catch (SQLException e) {
            assertThat(e, instanceOf(SQLException.class));
        }

        listener.notify(State.OFFLINE, State.ONLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(onlineConnection));

        listener.notify(State.ONLINE, State.OFFLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(offlineConnection));

        listener.notify(State.OFFLINE, State.NOT_AVAILABLE);
        try {
            onlineOfflineSwitchedDataSource.getConnection();
            throw new IllegalStateException();
        } catch (SQLException e) {
            assertThat(e, instanceOf(SQLException.class));
        }

        listener.notify(State.NOT_AVAILABLE, State.ONLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(onlineConnection));

        listener.notify(State.NOT_AVAILABLE, State.OFFLINE);
        assertThat(
            onlineOfflineSwitchedDataSource.getConnection(),
            sameInstance(offlineConnection));
    }
}
