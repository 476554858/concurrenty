package com.zjx.concurrenty.example.threadLocal;

/**
 * Created by Administrator on 2019/1/5.
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<Long>();
    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
