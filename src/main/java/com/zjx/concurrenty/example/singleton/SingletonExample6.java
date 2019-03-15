package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.ThreadSafe;

/**
 * 饿汉模式
 * 单利的实例在类装载使用时进行创建
 */
@ThreadSafe//但是性能会下降
public class SingletonExample6 {

    //私有的构造函数
    private SingletonExample6(){

    }
    private static SingletonExample6 instance = null;
    static {
        instance = new SingletonExample6();
    }
    //单利对象

    //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance.hashCode());
        System.out.println(instance.hashCode());
    }

}
