import org.junit.Assert;
import org.junit.Test;

public class HustleJDBCTests {

	@Test
	public void prepareStatementTest() {
		HustleDriver driver = new HustleDriver();
		HustleConnection conn = driver.connect("url", null);

		HustlePreparedStatement pstmt0 = conn.prepareStatement("DROP TABLE t;");
		pstmt0.executeQuery();

		HustlePreparedStatement pstmt1 = conn.prepareStatement("CREATE TABLE t (a BIGINT, b BIGINT);");
		pstmt1.executeQuery();

		HustlePreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO t VALUES (1, 2);");
		pstmt2.executeQuery();

		HustlePreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM t;");
		HustleResultSet resultSet = pstmt3.executeQuery();
		Assert.assertTrue(resultSet.next());
		Assert.assertEquals(1, resultSet.getLong(0));
		Assert.assertEquals(2, resultSet.getLong(1));
		Assert.assertFalse(resultSet.next());
	}
}
