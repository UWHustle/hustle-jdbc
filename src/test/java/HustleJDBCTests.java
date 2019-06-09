import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class HustleJDBCTests {

	@Test
	public void prepareStatementTest() {
		HustleDriver driver = new HustleDriver();
		HustleConnection conn = driver.connect("127.0.0.1:8000", null);

		try {
			conn.prepareStatement("DROP TABLE t;").executeUpdate();
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
