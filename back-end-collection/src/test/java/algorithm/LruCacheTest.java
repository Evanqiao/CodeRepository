package algorithm;

import static org.junit.Assert.*;

import algorithm.lru.BasedLinkedHashMapCache;
import algorithm.lru.LRUCache;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Test;

/**
 * @author qiaoyihan
 * @date 2020/6/24
 */
public class LruCacheTest {

    @Test
    public void testAccessOrder() {
        Map<Integer, String> map = new BasedLinkedHashMapCache<>(5);

        map.put(1, "1");
        map.put(2, "2");
        map.put(4, "4");
        map.put(5, "5");
        map.put(3, "3");

        map.get(4);
        assertEquals(Lists.newArrayList("1", "2", "5", "3", "4"), new ArrayList<>(map.values()));
    }

    @Test
    public void testRemove() {
        Map<Integer, String> map = new BasedLinkedHashMapCache<>(5);

        map.put(1, "1");
        map.put(2, "2");
        map.put(4, "4");
        map.put(5, "5");
        map.put(3, "3");

        map.get(1);

        map.put(6, "6");

        assertEquals(5, map.size());
        System.out.println(map);
    }

    @Test
    public void testInsertion1() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);

        cache.print();

        cache.put(2, 2);
        cache.print();
        cache.put(3, 3);
        cache.print();
        cache.get(1);
        cache.print();
        cache.put(4, 4);
        cache.print();
        assertEquals(1, cache.get(1));
        cache.print();
    }

    @Test
    public void testInsertion2() {
        LRUCache cache = new LRUCache(2);
        cache.get(2);
        cache.print();
        cache.put(2, 6);
        cache.print();
        cache.get(1);
        cache.print();

        cache.put(1, 5);
        cache.print();
        cache.put(1, 2);
        cache.print();
        cache.get(1);
        cache.print();
        cache.get(2);
        cache.print();
    }
}
