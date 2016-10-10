package learnHystrix.stream;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SupplierTest {

	public static void main(String[] args) {
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		//Another way
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).
		limit(10).forEach(System.out::println);

	}

}
