package com.resolvix.service.datasource;

import java.sql.*;

public class SqlExceptionUtils {

    private static final String SQLSTATE_CONNECTION_EXCEPTION_CLASS = "08";

    public static boolean isTransientConnectionException(SQLException e) {
        return (e instanceof SQLTransientConnectionException)
            || e.getSQLState().startsWith(SQLSTATE_CONNECTION_EXCEPTION_CLASS);
    }

    public static boolean isNonTransient(SQLException e) {
        return (e instanceof SQLNonTransientException);
    }

    public static boolean isRecoverable(SQLException e) {
        return (e instanceof SQLRecoverableException);
    }

    public static boolean isTransient(SQLException e) {
        return (e instanceof SQLTransientException);
    }
}
