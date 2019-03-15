package com.zjx.concurrenty.example.singleton;

import com.zjx.concurrenty.annocation.NotRecommend;
import com.zjx.concurrenty.annocation.ThreadSafe;

/**
 * 懒汉模式
 * 单利的实例在第一次创建的时候进行创建
 */
@ThreadSafe
@NotRecommend//会带来性能上的开销
public class SingletonExample3 {

    //私有的构造函数
    private SingletonExample3(){

    }
    //单利对象
    private static SingletonExample3 instance = null;
    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }


}
