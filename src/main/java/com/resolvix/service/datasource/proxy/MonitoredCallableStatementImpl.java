package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredCallableStatement;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.base.BaseMonitoredPreparedStatementProxyImpl;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class MonitoredCallableStatementImpl
    extends BaseMonitoredPreparedStatementProxyImpl<CallableStatement>
    implements MonitoredCallableStatement, CallableStatement
{

    private MonitoredCallableStatementImpl(CallableStatement statement, Monitor<Availability> monitor) {
        super(statement, monitor);
    }

    public static MonitoredCallableStatementImpl of(CallableStatement statement, Monitor<Availability> monitor) {
        return new MonitoredCallableStatementImpl(statement, monitor);
    }

    @Override
    protected void handleSqlException(SQLException e) {

    }

    //
    //  Wrapper
    //

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    //
    //  CallableStatement
    //

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, int.class, parameterIndex, int.class, sqlType);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, int.class, parameterIndex, int.class, sqlType, int.class, scale);
    }

    @Override
    public boolean wasNull() throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::wasNull);
    }

    @Override
    public String getString(int parameterIndex) throws SQLException {
        return invoke(String.class, SQLException.class, statement::getString, int.class, parameterIndex);
    }

    @Override
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::getBoolean, int.class, parameterIndex);
    }

    @Override
    public byte getByte(int parameterIndex) throws SQLException {
        return invoke(byte.class, SQLException.class, statement::getByte, int.class, parameterIndex);
    }

    @Override
    public short getShort(int parameterIndex) throws SQLException {
        return invoke(short.class, SQLException.class, statement::getShort, int.class, parameterIndex);
    }

    @Override
    public int getInt(int parameterIndex) throws SQLException {
        return invoke(int.class, SQLException.class, statement::getInt, int.class, parameterIndex);
    }

    @Override
    public long getLong(int parameterIndex) throws SQLException {
        return invoke(long.class, SQLException.class, statement::getLong, int.class, parameterIndex);
    }

    @Override
    public float getFloat(int parameterIndex) throws SQLException {
        return invoke(float.class, SQLException.class, statement::getFloat, int.class, parameterIndex);
    }

    @Override
    public double getDouble(int parameterIndex) throws SQLException {
        return invoke(double.class, SQLException.class, statement::getDouble, int.class, parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, statement::getBigDecimal, int.class, parameterIndex);
    }

    @Override
    public byte[] getBytes(int parameterIndex) throws SQLException {
        return invoke(byte[].class, SQLException.class, statement::getBytes, int.class, parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex) throws SQLException {
        return invoke(Date.class, SQLException.class, statement::getDate, int.class, parameterIndex);
    }

    @Override
    public Time getTime(int parameterIndex) throws SQLException {
        return invoke(Time.class, SQLException.class, statement::getTime, int.class, parameterIndex);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, statement::getTimestamp, int.class, parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex) throws SQLException {
        return invoke(Object.class, SQLException.class, statement::getObject, int.class, parameterIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, statement::getBigDecimal, int.class, parameterIndex);
    }

    @Override
    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return invoke(Object.class, SQLException.class, statement::getObject, int.class, parameterIndex, Map.class, map);
    }

    @Override
    public Ref getRef(int parameterIndex) throws SQLException {
        return invoke(Ref.class, SQLException.class, statement::getRef, int.class, parameterIndex);
    }

    @Override
    public Blob getBlob(int parameterIndex) throws SQLException {
        return invoke(Blob.class, SQLException.class, statement::getBlob, int.class, parameterIndex);
    }

    @Override
    public Clob getClob(int parameterIndex) throws SQLException {
        return invoke(Clob.class, SQLException.class, statement::getClob, int.class, parameterIndex);
    }

    @Override
    public Array getArray(int parameterIndex) throws SQLException {
        return invoke(Array.class, SQLException.class, statement::getArray, int.class, parameterIndex);
    }

    @Override
    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return invoke(Date.class, SQLException.class, statement::getDate, int.class, parameterIndex, Calendar.class, cal);
    }

    @Override
    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return invoke(Time.class, SQLException.class, statement::getTime, int.class, parameterIndex, Calendar.class, cal);
    }

    @Override
    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, statement::getTimestamp, int.class, parameterIndex, Calendar.class, cal);
    }

    @Override
    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, int.class, parameterIndex, int.class, sqlType, String.class, typeName);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, String.class, parameterName, int.class, sqlType);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, String.class, parameterName, int.class, sqlType, int.class, scale);
    }

    @Override
    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        invoke(SQLException.class, statement::registerOutParameter, String.class, parameterName, int.class, sqlType, String.class, typeName);
    }

    @Override
    public URL getURL(int parameterIndex) throws SQLException {
        return invoke(URL.class, SQLException.class, statement::getURL, int.class, parameterIndex);
    }

    @Override
    public void setURL(String parameterName, URL val) throws SQLException {
        invoke(SQLException.class, statement::setURL, String.class, parameterName, URL.class, val);
    }

    @Override
    public void setNull(String parameterName, int sqlType) throws SQLException {
        invoke(SQLException.class, statement::setNull, String.class, parameterName, int.class, sqlType);
    }

    @Override
    public void setBoolean(String parameterName, boolean x) throws SQLException {
        invoke(SQLException.class, statement::setBoolean, String.class, parameterName, boolean.class, x);
    }

    @Override
    public void setByte(String parameterName, byte x) throws SQLException {
        invoke(SQLException.class, statement::setByte, String.class, parameterName, byte.class, x);
    }

    @Override
    public void setShort(String parameterName, short x) throws SQLException {
        invoke(SQLException.class, statement::setShort, String.class, parameterName, short.class, x);
    }

    @Override
    public void setInt(String parameterName, int x) throws SQLException {
        invoke(SQLException.class, statement::setInt, String.class, parameterName, int.class, x);
    }

    @Override
    public void setLong(String parameterName, long x) throws SQLException {
        invoke(SQLException.class, statement::setLong, String.class, parameterName, long.class, x);
    }

    @Override
    public void setFloat(String parameterName, float x) throws SQLException {
        invoke(SQLException.class, statement::setFloat, String.class, parameterName, float.class, x);
    }

    @Override
    public void setDouble(String parameterName, double x) throws SQLException {
        invoke(SQLException.class, statement::setDouble, String.class, parameterName, double.class, x);
    }

    @Override
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        invoke(SQLException.class, statement::setBigDecimal, String.class, parameterName, BigDecimal.class, x);
    }

    @Override
    public void setString(String parameterName, String x) throws SQLException {
        invoke(SQLException.class, statement::setString, String.class, parameterName, String.class, x);
    }

    @Override
    public void setBytes(String parameterName, byte[] x) throws SQLException {
        invoke(SQLException.class, statement::setBytes, String.class, parameterName, byte[].class, x);
    }

    @Override
    public void setDate(String parameterName, Date x) throws SQLException {
        invoke(SQLException.class, statement::setDate, String.class, parameterName, Date.class, x);
    }

    @Override
    public void setTime(String parameterName, Time x) throws SQLException {
        invoke(SQLException.class, statement::setTime, String.class, parameterName, Time.class, x);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        invoke(SQLException.class, statement::setTimestamp, String.class, parameterName, Timestamp.class, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, String.class, parameterName, InputStream.class, x, int.class, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, String.class, parameterName, InputStream.class, x, int.class, length);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        invoke(SQLException.class, statement::setObject, String.class, parameterName, Object.class, x, int.class, targetSqlType, int.class, scale);
    }

    @Override
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        invoke(SQLException.class, statement::setObject, String.class, parameterName, Object.class, x, int.class, targetSqlType);
    }

    @Override
    public void setObject(String parameterName, Object x) throws SQLException {
        invoke(SQLException.class, statement::setObject, String.class, parameterName, Object.class, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, String.class, parameterName, Reader.class, reader, int.class, length);
    }

    @Override
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setDate, String.class, parameterName, Date.class, x, Calendar.class, cal);
    }

    @Override
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setTime, String.class, parameterName, Time.class, x, Calendar.class, cal);
    }

    @Override
    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        invoke(SQLException.class, statement::setTimestamp, String.class, parameterName, Timestamp.class, x, Calendar.class, cal);
    }

    @Override
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        invoke(SQLException.class, statement::setNull, String.class, parameterName, int.class, sqlType, String.class, typeName);
    }

    @Override
    public String getString(String parameterName) throws SQLException {
        return invoke(String.class, SQLException.class, statement::getString, String.class, parameterName);
    }

    @Override
    public boolean getBoolean(String parameterName) throws SQLException {
        return invoke(boolean.class, SQLException.class, statement::getBoolean, String.class, parameterName);
    }

    @Override
    public byte getByte(String parameterName) throws SQLException {
        return invoke(byte.class, SQLException.class, statement::getByte, String.class, parameterName);
    }

    @Override
    public short getShort(String parameterName) throws SQLException {
        return invoke(short.class, SQLException.class, statement::getShort, String.class, parameterName);
    }

    @Override
    public int getInt(String parameterName) throws SQLException {
        return invoke(int.class, SQLException.class, statement::getInt, String.class, parameterName);
    }

    @Override
    public long getLong(String parameterName) throws SQLException {
        return invoke(long.class, SQLException.class, statement::getLong, String.class, parameterName);
    }

    @Override
    public float getFloat(String parameterName) throws SQLException {
        return invoke(float.class, SQLException.class, statement::getFloat, String.class, parameterName);
    }

    @Override
    public double getDouble(String parameterName) throws SQLException {
        return invoke(double.class, SQLException.class, statement::getDouble, String.class, parameterName);
    }

    @Override
    public byte[] getBytes(String parameterName) throws SQLException {
        return invoke(byte[].class, SQLException.class, statement::getBytes, String.class, parameterName);
    }

    @Override
    public Date getDate(String parameterName) throws SQLException {
        return invoke(Date.class, SQLException.class, statement::getDate, String.class, parameterName);
    }

    @Override
    public Time getTime(String parameterName) throws SQLException {
        return invoke(Time.class, SQLException.class, statement::getTime, String.class, parameterName);
    }

    @Override
    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, statement::getTimestamp, String.class, parameterName);
    }

    @Override
    public Object getObject(String parameterName) throws SQLException {
        return invoke(Object.class, SQLException.class, statement::getObject, String.class, parameterName);
    }

    @Override
    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, statement::getBigDecimal, String.class, parameterName);
    }

    @Override
    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return invoke(Object.class, SQLException.class, statement::getObject, String.class, parameterName);
    }

    @Override
    public Ref getRef(String parameterName) throws SQLException {
        return invoke(Ref.class, SQLException.class, statement::getRef, String.class, parameterName);
    }

    @Override
    public Blob getBlob(String parameterName) throws SQLException {
        return invoke(Blob.class, SQLException.class, statement::getBlob, String.class, parameterName);
    }

    @Override
    public Clob getClob(String parameterName) throws SQLException {
        return invoke(Clob.class, SQLException.class, statement::getClob, String.class, parameterName);
    }

    @Override
    public Array getArray(String parameterName) throws SQLException {
        return invoke(Array.class, SQLException.class, statement::getArray, String.class, parameterName);
    }

    @Override
    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return invoke(Date.class, SQLException.class, statement::getDate, String.class, parameterName, Calendar.class, cal);
    }

    @Override
    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return invoke(Time.class, SQLException.class, statement::getTime, String.class, parameterName, Calendar.class, cal);
    }

    @Override
    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, statement::getTimestamp, String.class, parameterName, Calendar.class, cal);
    }

    @Override
    public URL getURL(String parameterName) throws SQLException {
        return invoke(URL.class, SQLException.class, statement::getURL, String.class, parameterName);
    }

    @Override
    public RowId getRowId(int parameterIndex) throws SQLException {
        return invoke(RowId.class, SQLException.class, statement::getRowId, int.class, parameterIndex);
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        return invoke(RowId.class, SQLException.class, statement::getRowId, String.class, parameterName);
    }

    @Override
    public void setRowId(String parameterName, RowId x) throws SQLException {
        invoke(SQLException.class, statement::setRowId, String.class, parameterName, RowId.class, x);
    }

    @Override
    public void setNString(String parameterName, String value) throws SQLException {
        invoke(SQLException.class, statement::setNString, String.class, parameterName, String.class, value);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        invoke(SQLException.class, statement::setNCharacterStream, String.class, parameterName, Reader.class, value, long.class, length);
    }

    @Override
    public void setNClob(String parameterName, NClob value) throws SQLException {
        invoke(SQLException.class, statement::setNClob, String.class, parameterName, NClob.class, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setClob, String.class, parameterName, Reader.class, reader, long.class, length);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        invoke(SQLException.class, statement::setBlob, String.class, parameterName, InputStream.class, inputStream, long.class, length);
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setNClob, String.class, parameterName, Reader.class, reader, long.class, length);
    }

    @Override
    public NClob getNClob(int parameterIndex) throws SQLException {
        return invoke(NClob.class, SQLException.class, statement::getNClob, int.class, parameterIndex);
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        return invoke(NClob.class, SQLException.class, statement::getNClob, String.class, parameterName);
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        invoke(SQLException.class, statement::setSQLXML, String.class, parameterName, SQLXML.class, xmlObject);
    }

    @Override
    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return invoke(SQLXML.class, SQLException.class, statement::getSQLXML, int.class, parameterIndex);
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return invoke(SQLXML.class, SQLException.class, statement::getSQLXML, String.class, parameterName);
    }

    @Override
    public String getNString(int parameterIndex) throws SQLException {
        return invoke(String.class, SQLException.class, statement::getNString, int.class, parameterIndex);
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        return invoke(String.class, SQLException.class, statement::getNString, String.class, parameterName);
    }

    @Override
    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return invoke(Reader.class, SQLException.class, statement::getNCharacterStream, int.class, parameterIndex);
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return invoke(Reader.class, SQLException.class, statement::getNCharacterStream, String.class, parameterName);
    }

    @Override
    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return invoke(Reader.class, SQLException.class, statement::getCharacterStream, int.class, parameterIndex);
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        return invoke(Reader.class, SQLException.class, statement::getCharacterStream, String.class, parameterName);
    }

    @Override
    public void setBlob(String parameterName, Blob x) throws SQLException {
        invoke(SQLException.class, statement::setBlob, String.class, parameterName, Blob.class, x);
    }

    @Override
    public void setClob(String parameterName, Clob x) throws SQLException {
        invoke(SQLException.class, statement::setClob, String.class, parameterName, Clob.class, x);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, String.class, parameterName, InputStream.class, x, long.class, length);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, String.class, parameterName, InputStream.class, x, long.class, length);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, String.class, parameterName, Reader.class, reader, long.class, length);
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        invoke(SQLException.class, statement::setAsciiStream, String.class, parameterName, InputStream.class, x);
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        invoke(SQLException.class, statement::setBinaryStream, String.class, parameterName, InputStream.class, x);
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setCharacterStream, String.class, parameterName, Reader.class, reader);
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        invoke(SQLException.class, statement::setNCharacterStream, String.class, parameterName, Reader.class, value);
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setClob, String.class, parameterName, Reader.class, reader);
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        invoke(SQLException.class, statement::setBlob, String.class, parameterName, InputStream.class, inputStream);
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        invoke(SQLException.class, statement::setNClob, String.class, parameterName, Reader.class, reader);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return (T) invoke(Object.class, SQLException.class, statement::getObject, int.class, parameterIndex, Class.class, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return (T) invoke(Object.class, SQLException.class, statement::getObject, String.class, parameterName, Class.class, type);
    }

    //
    //  MonitoredCallableStatement
    //

}
