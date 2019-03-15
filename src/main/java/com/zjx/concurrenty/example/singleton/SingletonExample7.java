package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.Recommend;
import com.zjx.concurrenty.annocation.ThreadSafe;


@ThreadSafe
@Recommend
public class SingletonExample7 {

    //私有的构造函数
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;
        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

}
