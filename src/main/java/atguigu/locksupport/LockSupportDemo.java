package atguigu.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {

    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        loclSupport();
    }

    private static void loclSupport() {
        final Thread a = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   TimeUnit.SECONDS.sleep(3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName() + "---come in" + System.currentTimeMillis());
               LockSupport.park();//被阻塞，需要许可证
               System.out.println(Thread.currentThread().getName() + "--被唤醒" + System.currentTimeMillis());
           }
       }, "a");
        a.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.unpark(a);//被阻塞，需要许可证(可以在阻塞之前执行)
                System.out.println(Thread.currentThread().getName() + "--通知了");
            }
        }, "b").start();
    }

    private static void lockAwaitSignal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "---come in");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "--被唤醒");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "---通知");
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        },"B").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectLock){
                    System.out.println(Thread.currentThread().getName() + "---come in");
                    try {
                        //放弃了锁和资源,所以B线程才能拿到锁
                        objectLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "--被唤醒");
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectLock){
                    try {
                        objectLock.notify();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "--通知");
                }
                }
        }, "B").start();
    }
}
