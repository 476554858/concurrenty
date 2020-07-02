package com.zjx.concurrenty.example.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalNamalUsage1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000);
                    SimpleDateFormat sdf = ThreadSafeFormatter.dateFormatThreadLocal.get();
                    System.out.println(sdf.format(date));
                }
            });
        }
    }
}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        }
    };
}
