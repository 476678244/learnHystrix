package learnHystrix.stream;

import java.util.Arrays;
import java.util.List;

public class SquareTest {

	public static void main(String[] args) {
		// Old way:
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		for (Integer n : list) {
			int x = n * n;
			System.out.println(x);
		}

		// New way:
		list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		list.stream().map((x) -> x * x).forEach(System.out::println);
		
		int sum = list.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
		System.out.println(sum);

		
	}

}
