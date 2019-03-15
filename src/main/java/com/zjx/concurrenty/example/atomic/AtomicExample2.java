package com.zjx.concurrenty.example.atomic;

import com.zjx.concurrenty.annocation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018/12/30.
 */
@Slf4j
@ThreadSafe
public class AtomicExample2 {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        count.addAndGet(1);
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
        log.info("count:{}",count);
    }
    /*private static void add(){
        count.addAndGet(1);
        //count.incrementAndGet();
        //count.getAndIncrement();
    }*/
}
