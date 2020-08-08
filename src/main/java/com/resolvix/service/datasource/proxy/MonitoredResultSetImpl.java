package com.resolvix.service.datasource.proxy;

import com.resolvix.lib.monitor.api.Monitor;
import com.resolvix.service.datasource.api.MonitoredResultSet;
import com.resolvix.service.datasource.api.monitor.Availability;
import com.resolvix.service.datasource.proxy.base.BaseStaticProxyImpl;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class MonitoredResultSetImpl
    extends BaseStaticProxyImpl
    implements MonitoredResultSet
{
    private ResultSet delegate;

    private Monitor<Availability> monitor;

    private MonitoredResultSetImpl(ResultSet resultSet, Monitor<Availability> monitor) {
        this.delegate = resultSet;
        this.monitor = monitor;
    }

    public static MonitoredResultSetImpl of(ResultSet resultSet, Monitor<Availability> monitor) {
        return new MonitoredResultSetImpl(resultSet, monitor);
    }

    //
    //  BaseStaticProxyImpl
    //

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
    //  ResultSet
    //

    @Override
    public boolean next() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::next);
    }

    @Override
    public void close() throws SQLException {
        invoke(SQLException.class, delegate::close);
    }

    @Override
    public boolean wasNull() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::wasNull);
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return invoke(String.class, SQLException.class, delegate::getString, int.class, columnIndex);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::getBoolean, int.class, columnIndex);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        return invoke(byte.class, SQLException.class, delegate::getByte, int.class, columnIndex);
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        return invoke(short.class, SQLException.class, delegate::getShort, int.class, columnIndex);
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getInt, int.class, columnIndex);
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        return invoke(long.class, SQLException.class, delegate::getLong, int.class, columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        return invoke(float.class, SQLException.class, delegate::getFloat, int.class, columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        return invoke(double.class, SQLException.class, delegate::getDouble, int.class, columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, delegate::getBigDecimal, int.class, columnIndex);
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        return invoke(byte[].class, SQLException.class, delegate::getBytes, int.class, columnIndex);
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        return invoke(Date.class, SQLException.class, delegate::getDate, int.class, columnIndex);
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        return invoke(Time.class, SQLException.class, delegate::getTime, int.class, columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, delegate::getTimestamp, int.class, columnIndex);
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getAsciiStream, int.class, columnIndex);
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getUnicodeStream, int.class, columnIndex);
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getBinaryStream, int.class, columnIndex);
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return invoke(String.class, SQLException.class, delegate::getString, String.class, columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::getBoolean, String.class, columnLabel);
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        return invoke(byte.class, SQLException.class, delegate::getByte, String.class, columnLabel);
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        return invoke(short.class, SQLException.class, delegate::getShort, String.class, columnLabel);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getInt, String.class, columnLabel);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        return invoke(long.class, SQLException.class, delegate::getLong, String.class, columnLabel);
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        return invoke(float.class, SQLException.class, delegate::getFloat, String.class, columnLabel);
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        return invoke(double.class, SQLException.class, delegate::getDouble, String.class, columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, delegate::getBigDecimal, String.class, columnLabel, int.class, scale);
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        return invoke(byte[].class, SQLException.class, delegate::getBytes, String.class, columnLabel);
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        return invoke(Date.class, SQLException.class, delegate::getDate, String.class, columnLabel);
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        return invoke(Time.class, SQLException.class, delegate::getTime, String.class, columnLabel);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, delegate::getTimestamp, String.class, columnLabel);
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getAsciiStream, String.class, columnLabel);
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getUnicodeStream, String.class, columnLabel);
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return invoke(InputStream.class, SQLException.class, delegate::getBinaryStream, String.class, columnLabel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return invoke(SQLWarning.class, SQLException.class, delegate::getWarnings);
    }

    @Override
    public void clearWarnings() throws SQLException {
        invoke(SQLException.class, delegate::clearWarnings);
    }

    @Override
    public String getCursorName() throws SQLException {
        return invoke(String.class, SQLException.class, delegate::getCursorName);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return invoke(ResultSetMetaData.class, SQLException.class, delegate::getMetaData);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        return invoke(Object.class, SQLException.class, delegate::getObject, int.class, columnIndex);
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return invoke(Object.class, SQLException.class, delegate::getObject, String.class, columnLabel);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        return invoke(int.class, SQLException.class, delegate::findColumn, String.class, columnLabel);
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return invoke(Reader.class, SQLException.class, delegate::getCharacterStream, int.class, columnIndex);
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        return invoke(Reader.class, SQLException.class, delegate::getCharacterStream, String.class, columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, delegate::getBigDecimal, int.class, columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return invoke(BigDecimal.class, SQLException.class, delegate::getBigDecimal, String.class, columnLabel);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::isBeforeFirst);
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::isAfterLast);
    }

    @Override
    public boolean isFirst() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::isFirst);
    }

    @Override
    public boolean isLast() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::isLast);
    }

    @Override
    public void beforeFirst() throws SQLException {
        invoke(SQLException.class, delegate::beforeFirst);
    }

    @Override
    public void afterLast() throws SQLException {
        invoke(SQLException.class, delegate::afterLast);
    }

    @Override
    public boolean first() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::first);
    }

    @Override
    public boolean last() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::last);
    }

    @Override
    public int getRow() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getRow);
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::absolute, int.class, row);
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::relative, int.class, rows);
    }

    @Override
    public boolean previous() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::previous);
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        invoke(SQLException.class, delegate::setFetchDirection, int.class, direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getFetchDirection);
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        invoke(SQLException.class, delegate::setFetchSize, int.class, rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getFetchSize);
    }

    @Override
    public int getType() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getType);
    }

    @Override
    public int getConcurrency() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getConcurrency);
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::rowUpdated);
    }

    @Override
    public boolean rowInserted() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::rowInserted);
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::rowDeleted);
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        invoke(SQLException.class, delegate::updateNull, int.class, columnIndex);
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        invoke(SQLException.class, delegate::updateBoolean, int.class, columnIndex, boolean.class, x);
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        invoke(SQLException.class, delegate::updateByte, int.class, columnIndex, byte.class, x);
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        invoke(SQLException.class, delegate::updateShort, int.class, columnIndex, short.class, x);
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        invoke(SQLException.class, delegate::updateInt, int.class, columnIndex, int.class, x);
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        invoke(SQLException.class, delegate::updateLong, int.class, columnIndex, long.class, x);
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        invoke(SQLException.class, delegate::updateFloat, int.class, columnIndex, float.class, x);
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        invoke(SQLException.class, delegate::updateDouble, int.class, columnIndex, double.class, x);
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        invoke(SQLException.class, delegate::updateBigDecimal, int.class, columnIndex, BigDecimal.class, x);
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        invoke(SQLException.class, delegate::updateString, int.class, columnIndex, String.class, x);
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        invoke(SQLException.class, delegate::updateBytes, int.class, columnIndex, byte[].class, x);
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        invoke(SQLException.class, delegate::updateDate, int.class, columnIndex, Date.class, x);
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        invoke(SQLException.class, delegate::updateTime, int.class, columnIndex, Time.class, x);
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        invoke(SQLException.class, delegate::updateTimestamp, int.class, columnIndex, Timestamp.class, x);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, int.class, columnIndex, InputStream.class, x, int.class, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, int.class, columnIndex, InputStream.class, x, int.class, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, int.class, columnIndex, Reader.class, x, int.class, length);
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        invoke(SQLException.class, delegate::updateObject, int.class, columnIndex, Object.class, x, int.class, scaleOrLength);
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        invoke(SQLException.class, delegate::updateObject, int.class, columnIndex, Object.class, x);
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        invoke(SQLException.class, delegate::updateNull, String.class, columnLabel);
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        invoke(SQLException.class, delegate::updateBoolean, String.class, columnLabel, boolean.class, x);
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        invoke(SQLException.class, delegate::updateByte, String.class, columnLabel, byte.class, x);
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        invoke(SQLException.class, delegate::updateShort, String.class, columnLabel, short.class, x);
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        invoke(SQLException.class, delegate::updateInt, String.class, columnLabel, int.class, x);
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        invoke(SQLException.class, delegate::updateLong, String.class, columnLabel, long.class, x);
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        invoke(SQLException.class, delegate::updateFloat, String.class, columnLabel, float.class, x);
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        invoke(SQLException.class, delegate::updateDouble, String.class, columnLabel, double.class, x);
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        invoke(SQLException.class, delegate::updateBigDecimal, String.class, columnLabel, BigDecimal.class, x);
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        invoke(SQLException.class, delegate::updateString, String.class, columnLabel, String.class, x);
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        invoke(SQLException.class, delegate::updateBytes, String.class, columnLabel, byte[].class, x);
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        invoke(SQLException.class, delegate::updateDate, String.class, columnLabel, Date.class, x);
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        invoke(SQLException.class, delegate::updateTime, String.class, columnLabel, Time.class, x);
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        invoke(SQLException.class, delegate::updateTimestamp, String.class, columnLabel, Timestamp.class, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, String.class, columnLabel, InputStream.class, x, int.class, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, String.class, columnLabel, InputStream.class, x, int.class, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, String.class, columnLabel, Reader.class, reader, int.class, length);
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        invoke(SQLException.class, delegate::updateObject, String.class, columnLabel, Object.class, x, int.class, scaleOrLength);
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        invoke(SQLException.class, delegate::updateObject, String.class, columnLabel, Object.class, x);
    }

    @Override
    public void insertRow() throws SQLException {
        invoke(SQLException.class, delegate::insertRow);
    }

    @Override
    public void updateRow() throws SQLException {
        invoke(SQLException.class, delegate::updateRow);
    }

    @Override
    public void deleteRow() throws SQLException {
        invoke(SQLException.class, delegate::deleteRow);
    }

    @Override
    public void refreshRow() throws SQLException {
        invoke(SQLException.class, delegate::refreshRow);
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        invoke(SQLException.class, delegate::cancelRowUpdates);
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        invoke(SQLException.class, delegate::moveToInsertRow);
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        invoke(SQLException.class, delegate::moveToCurrentRow);
    }

    @Override
    public Statement getStatement() throws SQLException {
        return invoke(Statement.class, SQLException.class, delegate::getStatement);
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return invoke(Object.class, SQLException.class, delegate::getObject, int.class, columnIndex, Map.class, map);
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        return invoke(Ref.class, SQLException.class, delegate::getRef, int.class, columnIndex);
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        return invoke(Blob.class, SQLException.class, delegate::getBlob, int.class, columnIndex);
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        return invoke(Clob.class, SQLException.class, delegate::getClob, int.class, columnIndex);
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        return invoke(Array.class, SQLException.class, delegate::getArray, int.class, columnIndex);
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return invoke(Object.class, SQLException.class, delegate::getObject, String.class, columnLabel, Map.class, map);
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        return invoke(Ref.class, SQLException.class, delegate::getRef, String.class, columnLabel);
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        return invoke(Blob.class, SQLException.class, delegate::getBlob, String.class, columnLabel);
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        return invoke(Clob.class, SQLException.class, delegate::getClob, String.class, columnLabel);
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        return invoke(Array.class, SQLException.class, delegate::getArray, String.class, columnLabel);
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return invoke(Date.class, SQLException.class, delegate::getDate, int.class, columnIndex, Calendar.class, cal);
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return invoke(Date.class, SQLException.class, delegate::getDate, String.class, columnLabel, Calendar.class, cal);
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return invoke(Time.class, SQLException.class, delegate::getTime, int.class, columnIndex, Calendar.class, cal);
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return invoke(Time.class, SQLException.class, delegate::getTime, String.class, columnLabel, Calendar.class, cal);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, delegate::getTimestamp, int.class, columnIndex, Calendar.class, cal);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return invoke(Timestamp.class, SQLException.class, delegate::getTimestamp, String.class, columnLabel, Calendar.class, cal);
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        return invoke(URL.class, SQLException.class, delegate::getURL, int.class, columnIndex);
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        return invoke(URL.class, SQLException.class, delegate::getURL, String.class, columnLabel);
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        invoke(SQLException.class, delegate::updateRef, int.class, columnIndex, Ref.class, x);
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        invoke(SQLException.class, delegate::updateRef, String.class, columnLabel, Ref.class, x);
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, int.class, columnIndex, Blob.class, x);
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, String.class, columnLabel, Blob.class, x);
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, int.class, columnIndex, Clob.class, x);
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, String.class, columnLabel, Clob.class, x);
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        invoke(SQLException.class, delegate::updateArray, int.class, columnIndex, Array.class, x);
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        invoke(SQLException.class, delegate::updateArray, String.class, columnLabel, Array.class, x);
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        return invoke(RowId.class, SQLException.class, delegate::getRowId, int.class, columnIndex);
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        return invoke(RowId.class, SQLException.class, delegate::getRowId, String.class, columnLabel);
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        invoke(SQLException.class, delegate::updateRowId, int.class, columnIndex, RowId.class, x);
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        invoke(SQLException.class, delegate::updateRowId, String.class, columnLabel, RowId.class, x);
    }

    @Override
    public int getHoldability() throws SQLException {
        return invoke(int.class, SQLException.class, delegate::getHoldability);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return invoke(boolean.class, SQLException.class, delegate::isClosed);
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        invoke(SQLException.class, delegate::updateNString, int.class, columnIndex, String.class, nString);
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        invoke(SQLException.class, delegate::updateNString, String.class, columnLabel, String.class, nString);
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, int.class, columnIndex, NClob.class, nClob);
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, String.class, columnLabel, NClob.class, nClob);
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        return invoke(NClob.class, SQLException.class, delegate::getNClob, int.class, columnIndex);
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        return invoke(NClob.class, SQLException.class, delegate::getNClob, String.class, columnLabel);
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        return invoke(SQLXML.class, SQLException.class, delegate::getSQLXML, int.class, columnIndex);
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        return invoke(SQLXML.class, SQLException.class, delegate::getSQLXML, String.class, columnLabel);
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        invoke(SQLException.class, delegate::updateSQLXML, int.class, columnIndex, SQLXML.class, xmlObject);
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        invoke(SQLException.class, delegate::updateSQLXML, String.class, columnLabel, SQLXML.class, xmlObject);
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        return invoke(String.class, SQLException.class, delegate::getNString, int.class, columnIndex);
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        return invoke(String.class, SQLException.class, delegate::getNString, String.class, columnLabel);
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        return invoke(Reader.class, SQLException.class, delegate::getNCharacterStream, int.class, columnIndex);
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        return invoke(Reader.class, SQLException.class, delegate::getNCharacterStream, String.class, columnLabel);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateNCharacterStream, int.class, columnIndex, Reader.class, x, long.class, length);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateNCharacterStream, String.class, columnLabel, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, int.class, columnIndex, InputStream.class, x, long.class, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, int.class, columnIndex, InputStream.class, x, long.class, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, int.class, columnIndex, Reader.class, x, long.class, length);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, String.class, columnLabel, InputStream.class, x, long.class, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, String.class, columnLabel, InputStream.class, x, long.class, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, String.class, columnLabel, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, int.class, columnIndex, InputStream.class, inputStream, long.class, length);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, String.class, columnLabel, InputStream.class, inputStream, long.class, length);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, int.class, columnIndex, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, String.class, columnLabel, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, int.class, columnIndex, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, String.class, columnLabel, Reader.class, reader, long.class, length);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        invoke(SQLException.class, delegate::updateNCharacterStream, int.class, columnIndex, Reader.class, x);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateNCharacterStream, String.class, columnLabel, Reader.class, reader);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, int.class, columnIndex, InputStream.class, x);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, int.class, columnIndex, InputStream.class, x);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, int.class, columnIndex, Reader.class, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        invoke(SQLException.class, delegate::updateAsciiStream, String.class, columnLabel, InputStream.class, x);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        invoke(SQLException.class, delegate::updateBinaryStream, String.class, columnLabel, InputStream.class, x);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateCharacterStream, String.class, columnLabel, Reader.class, reader);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, int.class, columnIndex, InputStream.class, inputStream);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        invoke(SQLException.class, delegate::updateBlob, String.class, columnLabel, InputStream.class, inputStream);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, int.class, columnIndex, Reader.class, reader);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateClob, String.class, columnLabel, Reader.class, reader);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, int.class, columnIndex, Reader.class, reader);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        invoke(SQLException.class, delegate::updateNClob, String.class, columnLabel, Reader.class, reader);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return (T) invoke(Object.class, SQLException.class, delegate::getObject, int.class, columnIndex, Class.class, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return (T) invoke(Object.class, SQLException.class, delegate::getObject, String.class, columnLabel, Class.class, type);
    }

    //
    //  MonitoredResultSet
    //
}
