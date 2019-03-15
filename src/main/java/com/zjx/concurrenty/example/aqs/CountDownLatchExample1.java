package com.zjx.concurrenty.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1 {
    private static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i = 0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(threadNum);
                    }catch (Exception e){
                        log.error("exception",e);
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();//等countDownLatch减到0时才会执行下面的程序
        log.info("finish");
        exec.shutdown();
    }
    private static void test(int threadNum)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
        Thread.sleep(100);
    }
}
