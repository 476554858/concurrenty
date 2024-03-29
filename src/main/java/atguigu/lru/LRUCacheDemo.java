package atguigu.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    public LRUCacheDemo(int capacity){
        //accessOrder(存取顺序):true时，才开启最近最少使用删除
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
        System.out.println(lruCacheDemo.keySet());
//        lruCacheDemo.put(4, "d");
//        System.out.println(lruCacheDemo.keySet());
//        lruCacheDemo.put(3, "c");
        lruCacheDemo.get(1);

        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.get(2);
        System.out.println(lruCacheDemo.keySet());
    }
}
