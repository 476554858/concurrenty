package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.ThreadSafe;

/**
 * 饿汉模式
 * 单利的实例在类装载使用时进行创建
 */
@ThreadSafe//但是性能会下降
public class SingletonExample2 {

    //私有的构造函数
    private SingletonExample2(){

    }
    //单利对象
    private static SingletonExample2 instance = new SingletonExample2();
    //静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
 

}
