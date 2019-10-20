package atguigu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019-07-07.
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        final BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t put 1");
                    blockingQueue.put("1");
                    System.out.println(Thread.currentThread().getName()+"\t put 2");
                    blockingQueue.put("2");
                    System.out.println(Thread.currentThread().getName()+"\t put 3");
                    blockingQueue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
