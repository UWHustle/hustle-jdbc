public class HustleJNI {
	static {
		System.loadLibrary("hustle_jni");
	}

	public static native long hustleConnectionNew(String url);

	public static native long hustleConnectionExecuteQuery(long connectionPtr, String sql);

	public static native int hustleConnectionExecuteUpdate(long connectionPtr, String sql);

	public static native boolean hustleResultHasRow(long resultPtr, int row);

	public static native long hustleResultGetLong(long resultPtr, int row, int col);
}
