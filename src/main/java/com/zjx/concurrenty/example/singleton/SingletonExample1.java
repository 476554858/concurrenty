package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.NotThreadSafe;

/**
 * 懒汉模式
 * 单利的实例在第一次创建的时候进行创建
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有的构造函数
    private SingletonExample1(){

    }
    //单利对象
    private static SingletonExample1 instance = null;
    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }


}
