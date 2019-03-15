package com.zjx.concurrenty.example.publish;

import com.zjx.concurrenty.annocation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by Administrator on 2019/1/2.
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a","b","d"};
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));

    }
}
