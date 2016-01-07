package learnHystrix.reference;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set1 = new HashSet<String>();
		set1.add("aaa");
		Set<String> set2 = set1;
		set1 = new HashSet<String>();
		System.out.println(set2.size());
	}

}
