package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.ThreadSafe;

/**
 * 懒汉模式 - 双重同步锁单利模式
 * 单利的实例在第一次创建的时候进行创建
 */
@ThreadSafe
public class SingletonExample5 {

    //私有的构造函数
    private SingletonExample5(){

    }
    //1.memory = allocate() 分配对象的内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //单利对象 volatile + 双重检测机制 -> 禁止指令重排
    private static volatile SingletonExample5 instance = null;
    //静态的工厂方法
    public static  SingletonExample5 getInstance(){
        if(instance == null){//双重检测机制
            synchronized (SingletonExample5.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
