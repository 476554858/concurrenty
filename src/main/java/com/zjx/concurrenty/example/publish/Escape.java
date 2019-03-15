package com.zjx.concurrenty.example.publish;

import com.zjx.concurrenty.annocation.NotRecommend;
import com.zjx.concurrenty.annocation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2019/1/2.
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
    }
    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
