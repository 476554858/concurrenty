package com.zjx.concurrenty.example.concurrent;

import com.zjx.concurrenty.annocation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/1/6.
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {

    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<Integer>();


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        update(count);
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception",e);
                    }
                    countDownLatch.countDown();

                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",set.size());
    }
    private static void update(int i){
        set.add(i);
    }

}
