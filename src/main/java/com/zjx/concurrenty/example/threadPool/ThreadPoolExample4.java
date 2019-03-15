package com.zjx.concurrenty.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");//3秒后执行该线程
            }
        },3, TimeUnit.SECONDS);


        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        },1,3, TimeUnit.SECONDS);//延迟1秒后每隔3秒钟执行一次，并且不知执行shutdown

        executorService.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("time run");
            }
        },new Date(),5*1000);//延迟0秒每隔5秒执行一次
    }
}
