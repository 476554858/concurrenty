package com.zjx.concurrenty.example.syncContainer;

import com.zjx.concurrenty.annocation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by Administrator on 2019/1/7.
 */
@Slf4j
@NotThreadSafe
public class VectorExample3 {
    private static void test1(Vector<Integer> v1){//java.util.ConcurrentModificationException
        for (Integer i : v1){
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v1){//java.util.ConcurrentModificationException
        Iterator<Integer> iterator = v1.iterator();
       while (iterator.hasNext()){
           Integer i = iterator.next();
           if(i.equals(3)){
               v1.remove(i);
           }
       }
    }

    private static void test3(Vector<Integer> v1){
        for (int i = 0;i<v1.size();i++){
            v1.remove(i);
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<Integer>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test3(vector);
    }

}
