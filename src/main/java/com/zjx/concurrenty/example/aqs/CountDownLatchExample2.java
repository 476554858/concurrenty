package com.zjx.concurrenty.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {
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
        countDownLatch.await(10, TimeUnit.MILLISECONDS);//这里加上时间，不等线程执行完就执行下面的代码，
        log.info("finish");
        exec.shutdown();//将当前已有的线程先执行完才会关闭线程池
    }
    private static void test(int threadNum)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
