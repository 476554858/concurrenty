package com.zjx.concurrenty.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        new Thread(){
            public void run(){
                try {
                    reentrantLock.lock();
                    log.info("wait signal");//1
                    condition.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
                log.info("get signal");//4
                reentrantLock.unlock();
            }
        }.start();

        new Thread(){
            public void run(){
                reentrantLock.lock();
                log.info("get lock");//2
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                condition.signalAll();
                log.info("send signal ~ ");//3
                reentrantLock.unlock();
            }
        }.start();

    }



}
