package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.NotThreadSafe;

/**
 * 懒汉模式 - 双重同步锁单利模式
 * 单利的实例在第一次创建的时候进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有的构造函数
    private SingletonExample4(){

    }
    //1.memory = allocate() 分配对象的内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //JVM和cpu优化，发生了指令重排

    //1.memory = allocate() 分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance() 初始化对象

    //单利对象
    private static SingletonExample4 instance = null;
    //静态的工厂方法
    public static  SingletonExample4 getInstance(){
        if(instance == null){//双重检测机制
            synchronized (SingletonExample4.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
