package atguigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment()throws Exception{
        lock.lock();
        try {
            //1 判断
            while (number != 0){
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement()throws Exception{
        lock.lock();
        try {
            //1 判断
            while (number == 0){
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}

public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
      final ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        shareData.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"BB").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        shareData.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"DD").start();
    }
}
