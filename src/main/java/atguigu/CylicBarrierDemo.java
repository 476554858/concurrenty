package atguigu;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2019-07-06.
 */
public class CylicBarrierDemo {
    public static void main(String[] args) {

         final CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("召唤神龙");
            }
        });
        for (int i = 1; i <= 7; i++) {
            final  int temint = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"\t 手机到第:"+temint+"龙珠");
                    try {
                        cyclicBarrier.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },String.valueOf(i)).start();
        }

    }
}
