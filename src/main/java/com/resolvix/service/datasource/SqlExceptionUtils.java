package com.resolvix.service.datasource;

import java.sql.SQLException;

public class SqlExceptionUtils {

    private static final String SQLSTATE_CONNECTION_EXCEPTION_CLASS = "08";

    public static boolean isConnectionException(SQLException e) {
        return e.getSQLState()
            .startsWith(SQLSTATE_CONNECTION_EXCEPTION_CLASS);
    }
}
