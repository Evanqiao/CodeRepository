package algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qiaoyihan
 * @date 2020/6/24
 */
public class BasedLinkedHashMapCache<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    public BasedLinkedHashMapCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    /** 判断元素个数是否超过缓存容量 */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}
