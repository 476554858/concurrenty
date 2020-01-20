package atguigu;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("开始计算");
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread());
        new Thread(futureTask,"AA").start();
        new Thread(futureTask2,"BB").start();
        int result1 = futureTask.get();
        int result2 = futureTask.get();

        System.out.println("result1:"+result1+"\t"+"result2:"+result2);
    }
}
