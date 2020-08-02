package com.resolvix.service.datasource.base.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredPreparedStatement;
import com.resolvix.service.datasource.api.monitor.Availability;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public abstract class BaseMonitoredPreparedStatementProxyImpl<P extends PreparedStatement>
    extends BaseMonitoredStatementProxyImpl<P>
    implements MonitoredPreparedStatement, PreparedStatement
{

    protected BaseMonitoredPreparedStatementProxyImpl(P statement, Monitor<Availability> monitor) {
        super(statement, monitor);
    }

    //
    //  PreparedStatement
    //

    @Override
    public void clearParameters() throws SQLException {
        invoke(SQLException.class, statement::clearParameters);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        invoke(SQLException.class, statement::setNull, int.class, parameterIndex, int.class, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        invoke(SQLException.class, statement::setBoolean, int.class, parameterIndex, boolean.class, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        invoke(SQLException.class, statement::setByte, int.class, parameterIndex, byte.class, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        invoke(SQLException.class, statement::setShort, int.class, parameterIndex, short.class, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        invoke(SQLException.class, statement::setInt, int.class, parameterIndex, int.class, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        invoke(SQLException.class, statement::setLong, int.class, parameterIndex, long.class, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        invoke(SQLException.class, statement::setFloat, int.class, parameterIndex, float.class, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        invoke(SQLException.class, statement::setDouble, int.class, parameterIndex, double.class, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        invoke(SQLException.class, statement::setBigDecimal, int.class, parameterIndex, BigDecimal.class, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        invoke(SQLException.class, statement::setString, int.class, parameterIndex, String.class, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        invoke(SQLException.class, statement::setBytes, int.class, parameterIndex, byte[].class, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        invoke(SQLException.class, statement::setDate, int.class, parameterIndex, Date.class, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        invoke(SQLException.class, statement::setTime, int.class, parameterIndex, Time.class, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        invoke(SQLException.class, statement::setTimestamp, int.class, parameterIndex, Timestamp.class, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, int.class, parameterIndex, InputStream.class, x, int.class, length);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, statement::setUnicodeStream, int.class, parameterIndex, InputStream.class, x, int.class, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, int.class, parameterIndex, InputStream.class, x, int.class, length);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        invoke(SQLException.class, statement::setObject, int.class, parameterIndex, Object.class, x, int.class, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        invoke(SQLException.class, statement::setObject, int.class, parameterIndex, Object.class, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        invoke(SQLException.class, statement::setBlob, int.class, parameterIndex, Blob.class, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        invoke(SQLException.class, statement::setClob, int.class, parameterIndex, Clob.class, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        invoke(SQLException.class, statement::setArray, int.class, parameterIndex, Array.class, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setDate, int.class, parameterIndex, Date.class, x, Calendar.class, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setTime, int.class, parameterIndex, Time.class, x, Calendar.class, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setTimestamp, int.class, parameterIndex, Timestamp.class, x, Calendar.class, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        invoke(SQLException.class, statement::setNull, int.class, parameterIndex, int.class, sqlType, String.class, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        invoke(SQLException.class, statement::setURL, int.class, parameterIndex, URL.class, x);
    }

    @Override
    public boolean execute() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::execute);
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return invoke(ResultSet.class, SQLException.class, statement::executeQuery);
    }

    @Override
    public int executeUpdate() throws SQLException {
        return invoke(int.class, SQLException.class, statement::executeUpdate);
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        return invoke(long.class, SQLException.class, statement::executeLargeUpdate);
    }

    @Override
    public void addBatch() throws SQLException {
        invoke(SQLException.class, statement::addBatch);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, int.class, parameterIndex, Reader.class, reader, int.class, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        invoke(SQLException.class, statement::setRef, int.class, parameterIndex, Ref.class, x);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        invoke(SQLException.class, statement::setRowId, int.class, parameterIndex, RowId.class, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        invoke(SQLException.class, statement::setNString, int.class, parameterIndex, String.class, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        invoke(SQLException.class, statement::setNCharacterStream, int.class, parameterIndex, Reader.class, value, long.class, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        invoke(SQLException.class, statement::setNClob, int.class, parameterIndex, NClob.class, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setClob, int.class, parameterIndex, Reader.class, reader, long.class, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        invoke(SQLException.class, statement::setBlob, int.class, parameterIndex, InputStream.class, inputStream, long.class, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setNClob, int.class, parameterIndex, Reader.class, reader, long.class, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        invoke(SQLException.class, statement::setSQLXML, int.class, parameterIndex, SQLXML.class, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        invoke(SQLException.class, statement::setObject, int.class, parameterIndex, Object.class, x, int.class, targetSqlType, int.class, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, int.class, parameterIndex, InputStream.class, x, long.class, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, int.class, parameterIndex, InputStream.class, x, long.class, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, int.class, parameterIndex, Reader.class, reader, long.class, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, int.class, parameterIndex, InputStream.class, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, int.class, parameterIndex, InputStream.class, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, int.class, parameterIndex, Reader.class, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        invoke(SQLException.class, statement::setNCharacterStream, int.class, parameterIndex, Reader.class, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setClob, int.class, parameterIndex, Reader.class, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        invoke(SQLException.class, statement::setBlob, int.class, parameterIndex, InputStream.class, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setNClob, int.class, parameterIndex, Reader.class, reader);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        invoke(SQLException.class, statement::setObject, int.class, parameterIndex, Object.class, x, SQLType.class, targetSqlType, int.class, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        invoke(SQLException.class, statement::setObject, int.class, parameterIndex, Object.class, x, SQLType.class, targetSqlType);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return invoke(ResultSetMetaData.class, SQLException.class, statement::getMetaData);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return invoke(ParameterMetaData.class, SQLException.class, statement::getParameterMetaData);
    }

    //
    //  MonitoredPreparedStatement
    //
}
