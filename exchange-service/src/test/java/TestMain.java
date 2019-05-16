import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/5/7 10:53
 * @Description TestMain
 */
public class TestMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:META-INF/spring/applicationContext.xml"});
		context.start();
	}
}
