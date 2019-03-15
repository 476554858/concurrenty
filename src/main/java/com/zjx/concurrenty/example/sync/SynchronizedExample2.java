package com.zjx.concurrenty.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/1/1.
 */
@Slf4j
public class SynchronizedExample2 {
    //修饰一个代码块
    public void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i=0;i<10;i++){
                log.info("test1 - {} - {}",j,i);
            }
        }
    }
    //修饰一个方法
    public static synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 - {} - {}",j,i);
        }
    }
    public static void main(String[] args){
        //如果是相同对象或者有static修饰的话的话就是同步的
        final SynchronizedExample2 example1 = new SynchronizedExample2();
        final SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1(1);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example2.test1(2);
            }
        });
    }
}
