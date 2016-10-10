package learnHystrix.stream;

import java.util.function.Consumer;

public class RunnableTest {

	public static void main(String[] args) {
		Runnable r = () -> System.out.println("hello world");

		new Thread(r).start();
		
		r.run();
		
		Consumer<Integer>  c = (Integer x) -> { System.out.println(x); };
		c.accept(20);
		c.andThen(c);

	}

}
