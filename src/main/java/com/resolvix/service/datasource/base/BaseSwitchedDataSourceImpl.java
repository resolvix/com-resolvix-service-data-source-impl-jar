package com.resolvix.service.datasource.base;

import com.resolvix.service.datasource.api.SelectorListener;
import com.resolvix.service.datasource.api.SwitchedDataSource;
import com.resolvix.service.datasource.api.event.Change;
import com.resolvix.service.datasource.api.selector.Selector;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class BaseSwitchedDataSourceImpl<S>
    implements SwitchedDataSource<S>
{
    private Selector<S> selector;

    private volatile S state;

    private SelectorListener<S> listener;

    protected BaseSwitchedDataSourceImpl(Selector<S> selector) {
        this.selector = selector;
        this.state = selector.getState();
        this.listener = new Listener();
        this.selector.addListener(listener);
    }

    private class Listener
        implements SelectorListener<S> {

        @Override
        public void updateState(S state) {
            synchronized (BaseSwitchedDataSourceImpl.this) {
                BaseSwitchedDataSourceImpl.this.state = state;
            }
        }
    }

    //
    //  DataSource
    //

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }

    //
    //  SwitchedDataSource<S>
    //

    @Override
    public Selector<S> getSelector() {
        return selector;
    }

    @Override
    public synchronized S getState() {
        return state;
    }

    //
    //  RecentChangeHistory
    //

    @Override
    public List<Change> getRecentChangeHistory() {
        return Collections.unmodifiableList(
            getSelector().getRecentChangeHistory());
    }

    //
    //  Object
    //

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.selector.removeListener(listener);
    }
}