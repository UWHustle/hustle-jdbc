import io.hustle.HustleConnection;
import io.hustle.HustleResultSet;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class HustleJDBCTests {

	@Test
	public void prepareStatementTest() {
		try {
			Class.forName("io.hustle.HustleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			System.out.println(drivers.nextElement().toString());
		}

		try {
			HustleConnection conn = (HustleConnection) DriverManager.getConnection("jdbc:hustle://127.0.0.1:8000", "user", "password");
			conn.prepareStatement("DROP TABLE IF EXISTS t;").executeUpdate();
			conn.prepareStatement("CREATE TABLE t (a BIGINT, b BIGINT);").executeUpdate();
			conn.prepareStatement("INSERT INTO t VALUES (1, 2);").executeUpdate();
			conn.prepareStatement("INSERT INTO t VALUES (3, 4);").executeUpdate();
			HustleResultSet resultSet = conn.prepareStatement("SELECT * FROM t;").executeQuery();

			Assert.assertTrue(resultSet.next());
			Assert.assertEquals(1, resultSet.getLong(0));
			Assert.assertEquals(2, resultSet.getLong(1));

			Assert.assertTrue(resultSet.next());
			Assert.assertEquals(3, resultSet.getLong(0));
			Assert.assertEquals(4, resultSet.getLong(1));

			Assert.assertFalse(resultSet.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
