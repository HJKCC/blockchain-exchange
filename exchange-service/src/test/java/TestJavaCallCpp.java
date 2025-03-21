/**
 * @author chencheng0816@gmail.com
 * @date 2019/8/22 10:25
 * @Description TestJavaCallCpp
 */
public class TestJavaCallCpp {
	static {
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("javaCallcpp");
	}

	public native String DLL_RSA_GETPUBLICKEY(String content, String publicKey);
	public native String DLL_HMAC(String random, String pass);

	public static void main(String[] args) {
		TestJavaCallCpp testJavaCallCpp = new TestJavaCallCpp();
		System.out.println(testJavaCallCpp.DLL_HMAC("123", "abc"));
	}
}
