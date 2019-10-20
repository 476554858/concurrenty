package atguigu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){
            data =  atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2l, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入数据"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入数据"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，标示FLAT=true，生产动作结束");
    }

    public void myConsumer()throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2l,TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                //FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒钟没有取到蛋糕消费退出");
                System.out.println();
                //return;
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 消费队列蛋糕"+result+"成功");
            }

        }

    }

    public void stop()throws Exception{
        this.FLAG = false;
    }

}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        final MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myProd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"Prod").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"Counsumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒钟时间到，大老板main线程叫停");
        try {
            myResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
