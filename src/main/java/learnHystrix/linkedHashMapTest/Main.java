package learnHystrix.linkedHashMapTest;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wuzonghan on 2017/7/6.
 */
public class Main {

	public static void main(String[] args) {
		Map<String, Object> map = new LinkedHashMap<String, Object>(16, 0.75F, true);

		for (int i = 1; i <= 5; i++) {
			map.put(i + "", i);
		}
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getValue());
		}

		map.get("2");
		map.get("3");
		System.out.println("===============split line==================");

		Iterator<Map.Entry<String, Object>> iterator2 = map.entrySet().iterator();
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next().getValue());
		}

		Iterator<String> iterator3 = map.keySet().iterator();
		while (iterator3.hasNext()) {
			System.out.println(map.get(iterator3.next()));
		}
	}
}
