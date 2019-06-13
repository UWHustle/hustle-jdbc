package io.hustle;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class HustlePreparedStatement extends HustleStatement implements PreparedStatement {
	private String[] statement;
	private String[] params;

	HustlePreparedStatement(HustleConnection conn, String sql) {
		super(conn);
		statement = sql.split(Pattern.quote("?"), -1);
		params = new String[statement.length - 1];
	}

	@Override
	public HustleResultSet executeQuery() throws SQLException {
		String sql = bindParams();
		return super.executeQuery(sql);
	}

	@Override
	public int executeUpdate() throws SQLException {
		String sql = bindParams();
		return super.executeUpdate(sql);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setByte(int parameterIndex, byte x) {
		setParam(parameterIndex, String.valueOf(x));
	}

	@Override
	public void setShort(int parameterIndex, short x) {
		setParam(parameterIndex, String.valueOf(x));
	}

	@Override
	public void setInt(int parameterIndex, int x) {
		setParam(parameterIndex, String.valueOf(x));
	}

	@Override
	public void setLong(int parameterIndex, long x) {
		setParam(parameterIndex, String.valueOf(x));
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDouble(int parameterIndex, double x) {
		setParam(parameterIndex, String.valueOf(x));
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setString(int parameterIndex, String x) {
		setParam(parameterIndex, '\'' + x + '\'');
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void clearParameters() {
		Arrays.fill(params, null);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public boolean execute() throws SQLException {
		String sql = bindParams();
		return super.execute(sql);
	}

	@Override
	public void addBatch() throws SQLException {
		String sql = bindParams();
		super.addBatch(sql);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	private void setParam(int parameterIndex, String x) {
		params[parameterIndex - 1] = x;
	}

	private String bindParams() throws SQLException {
		for (String param : params) {
			if (param == null || param.isEmpty()) {
				throw new SQLException("Statement has unbound parameters");
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			sb.append(statement[i]);
			sb.append(params[i]);
		}
		sb.append(statement[statement.length - 1]);
		return sb.toString();
	}
}
