package com.zjx.concurrenty.example.immutable;

import com.google.common.collect.Maps;
import com.zjx.concurrenty.annocation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
/**
 * Created by Administrator on 2019/1/4.
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        //a = 2;
        //b= "3";
        //map = Maps.newHashMap(); //虽然这个引用不可以修改，
        // 但是里对象里面的值能修改,所以这里是线程不安全的
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    private void test(final  int a ){
        //a = 1;
    }
}
