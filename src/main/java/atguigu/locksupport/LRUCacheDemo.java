package atguigu.locksupport;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    public LRUCacheDemo(int capacity){
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");
        lruCacheDemo.put(4, "d");
        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());
    }
}
