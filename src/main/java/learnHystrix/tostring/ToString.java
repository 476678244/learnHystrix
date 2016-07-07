package learnHystrix.tostring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by I303152 on 6/21/2016.
 */
public class ToString {
    public static void main(String[] args) {
        Map<Long, Set<Long>> obj = new HashMap<>();
        obj.put(1L, new HashSet<>());
        obj.put(2L, new HashSet<>());
        obj.put(3L, new HashSet<>());
        obj.get(1L).add(1L);
        obj.get(1L).add(2L);
        obj.get(1L).add(3L);
        obj.get(1L).add(4L);
        obj.get(2L).add(5L);
        obj.get(2L).add(6L);
        obj.get(3L).add(7L);
        obj.get(3L).add(8L);
        obj.get(3L).add(9L);
        obj.get(3L).add(10L);
        obj.get(3L).add(11L);
        System.out.println(obj);
    }
}
