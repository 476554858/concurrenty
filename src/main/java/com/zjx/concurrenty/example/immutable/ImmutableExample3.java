package com.zjx.concurrenty.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Created by Administrator on 2019/1/4.
 */

public class ImmutableExample3 {
    private final static ImmutableList list = ImmutableList.of(1,2,3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).build();
    public static void main(String[] args) {
        map2.put(1,4);
        System.out.println(map2.get(3));
    }
}
