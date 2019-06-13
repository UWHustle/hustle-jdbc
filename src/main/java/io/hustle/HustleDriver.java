package io.hustle;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class HustleDriver implements Driver {

	private static final String PREFIX = "jdbc:hustle://";

	static {
		try {
			DriverManager.registerDriver(new HustleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public HustleConnection connect(String url, Properties info) {
		if (!acceptsURL(url)) {
			return null;
		}

		url = url.trim();
		return new HustleConnection(url.substring(PREFIX.length()));
	}

	@Override
	public boolean acceptsURL(String url) {
		return url != null && url.toLowerCase().startsWith(PREFIX);
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public int getMajorVersion() {
		return 1;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}
}
