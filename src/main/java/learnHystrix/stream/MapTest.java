package learnHystrix.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapTest {

	public static void main(String[] args) {
		List<String> wordList = new ArrayList<String>();
		wordList.add("a");
		wordList.add("b");
		List<String> output = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(output);

		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
		System.out.println(squareNums);
		
		// 使用lambda表达式
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);

	}

}
