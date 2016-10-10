package learnHystrix.stream;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		String strA = " abcd ", strB = null;
		print(strA);
		print("");
		print(strB);
		System.out.println(getLength(strA));
		System.out.println(getLength(""));
		System.out.println(getLength(strB));

	}

	public static void print(String text) {
		// Java 8
		Optional.ofNullable(text).ifPresent(System.out::println);
		// Pre-Java 8
		// if (text != null) {
		// System.out.println(text);
		// }
	}

	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	};

}
