import org.junit.Test;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/12/23 10:52
 * @Description TestJ2SE
 */
public class TestJ2SE {
	@Test
	public void testFinal() {
		TestJ2SE myClass = new TestJ2SE();
		StringBuffer buffer = new StringBuffer("hello");
		myClass.changeBuffer(buffer);
		System.out.println("StringBuffer: " + buffer.toString());

		String str = "hello";
		myClass.change(str);
		System.out.println("String: " + str);
	}

	void changeBuffer(final StringBuffer buffer) {
		buffer.append("world");
	}

	void change(String str) {
		str += "world";
	}
}
