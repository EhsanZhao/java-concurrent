package com.ehsanzhao.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 如果该线程阻塞的调用wait() ， wait(long) ，或wait(long, int)的方法Object类，
 * 或的join() ， join(long) ， join(long, int) ， sleep(long) ，或sleep(long, int) ，这个类的方法，
 * 那么它的  中断状态将被清除  ，并且将收到  InterruptedException  。
 *
 * 所以报错后需要二次中断，否则线程不会中断，会陷入死循环
 * @author zhaoyuan
 * @date 2023/3/14
 */
public class InterruptDemo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "=线程中断");
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //报错后中断状态将被清除,需要二次中断，否则线程不会中断，会陷入死循环
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=线程运行");
            }
        });
        t1.start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            t1.interrupt();
        }).start();
    }

}
