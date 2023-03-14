package com.ehsanzhao.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断
 * interrupt只是设置中断标识位，不会立即中断线程，必须线程检测到中断标识位设置位true，才会进行操作
 * 且线程结束后，设置中断标识位，不会有任何影响
 * 中断不活动的线程不会产生任何影响。
 * @author zhaoyuan
 * @date 2023/3/14
 */
public class InterruptDemo2 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println(Thread.currentThread().getName() + "-02-" + Thread.currentThread().isInterrupted());
            }
        },"t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + "-01-" + t1.isInterrupted()); // false
//        try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(()->{
            t1.interrupt();
        },"t2").start();

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + "-03-" + t1.isInterrupted());  //false 中断不活动的线程不会产生任何影响。
    }

}
