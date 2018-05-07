package learnHystrix.linkedHashMapTest;

import java.util.LinkedHashMap;

/**
 * Created by wuzonghan on 2017/7/6.
 */
public class LRUCache extends LinkedHashMap {
	public LRUCache(int maxSize) {
		super(maxSize, 0.75F, true);
		maxElements = maxSize;
	}

	protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
		return size() > maxElements;
	}

	private static final long serialVersionUID = 1L;
	protected int maxElements;
}
