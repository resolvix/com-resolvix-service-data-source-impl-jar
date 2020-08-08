package com.resolvix.service.datasource.base;

import com.resolvix.lib.event.api.Change;
import com.resolvix.lib.event.api.Listener;
import com.resolvix.lib.junction.api.Selector;
import com.resolvix.lib.junction.base.BaseJunctionImpl;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class BaseSwitchedDataSourceImpl<P, S extends Selector<P>, R extends DataSource>
    extends BaseJunctionImpl<P, S, R>
    implements DataSource
{
    private LocalListener listener;

    protected BaseSwitchedDataSourceImpl(P initialState, S selector) {
        super(initialState, selector);
        this.listener = new LocalListener();
        selector.addListener(listener);
    }

    private class LocalListener
        implements Listener<P> {

        @Override
        public void signal(P state) {
            BaseSwitchedDataSourceImpl.this.setState(state);
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
    //  RecentChangeHistory
    //

    @Override
    public List<Change<P>> getRecentChangeHistory() {
        return Collections.unmodifiableList(
            getSelector().getRecentChangeHistory());
    }
}