import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/5/7 10:53
 * @Description TestMain
 */
public class TestMain {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:META-INF/spring/applicationContext.xml"});
		context.start();


		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "end";
			}
		});
		String res1 = future.get();

		List<String> res = new ArrayList<>();
		CountDownLatch downLatch = new CountDownLatch(1);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				res.add("end");
				downLatch.countDown();
			}
		});
		downLatch.await();
	}
}
