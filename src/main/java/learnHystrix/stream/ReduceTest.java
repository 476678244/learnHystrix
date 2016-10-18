package learnHystrix.stream;

import java.util.Arrays;
import java.util.List;

public class ReduceTest {

	public static void main(String[] args) {
		// 新方法：
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).
				reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);

	}

}
