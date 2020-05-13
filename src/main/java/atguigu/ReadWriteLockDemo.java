package atguigu;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2020/5/10.
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {
        final MyCache myCache = new MyCache();

        for (int i=1;i<=5;i++){
            final String temp = i+"";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.put(temp,temp);
                }
            },i+"写线程").start();
        }

        for (int i=1;i<=5;i++){
            final String temp = i+"";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.get(temp);
                }
            },i+"读线程").start();
        }
    }

    static class MyCache{
        public volatile Map<String,Object> cache = new HashMap<String, Object>();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void put(String key,Object value){
            readWriteLock.writeLock().lock();
            try {
                Thread t = Thread.currentThread();
                System.out.println(t.getName()+"开始put");
                TimeUnit.MICROSECONDS.sleep(300);
                cache.put(key,value);
                System.out.println(t.getName()+"put完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }


        }

        public void get(String key){
            readWriteLock.readLock().lock();
            try {
                Thread t = Thread.currentThread();
                System.out.println(t.getName()+"开始读取");
                TimeUnit.MICROSECONDS.sleep(300);
                System.out.println(t.getName()+"读取到值："+cache.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }

        }
    }
}
