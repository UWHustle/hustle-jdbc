public class HustleJNI {
	static {
		System.loadLibrary("hustle_jni");
	}

	public static native long hustleConnectionNew();

	public static native long hustleConnectionPrepare(long connectionPtr, String sql);

	public static native long hustlePreparedStatementExecuteQuery(long preparedStatementPtr);

	public static native boolean hustleResultNext(long resultPtr);

	public static native long hustleResultGetLong(long resultPtr, int columnIndex);
}
