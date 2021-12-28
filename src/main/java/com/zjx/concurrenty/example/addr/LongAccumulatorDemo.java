package com.zjx.concurrenty.example.addr;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x * y, 1);
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10).forEach(i -> threadPool.execute(() -> accumulator.accumulate(i)));
        threadPool.shutdown();
        while (threadPool.isTerminated()){

        }
        System.out.println(accumulator.getThenReset());
    }
}
