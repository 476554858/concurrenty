package atguigu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019-07-12.
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            //模拟10个用户来办理业务，每个用户就是一个外部的请求线程
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
