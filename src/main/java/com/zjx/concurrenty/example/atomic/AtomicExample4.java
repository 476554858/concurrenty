package com.zjx.concurrenty.example.atomic;

import com.zjx.concurrenty.annocation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2018/12/30.
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2); //2
        count.compareAndSet(0,1); //no
        count.compareAndSet(1,3); //no
        count.compareAndSet(2,4); //4
        count.compareAndSet(3,5); //no
        log.info("count:{}",count.get());

    }

}
