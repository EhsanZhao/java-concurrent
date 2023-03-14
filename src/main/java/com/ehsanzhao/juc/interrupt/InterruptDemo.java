package com.ehsanzhao.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程中断
 * 中断标识！！！
 * 1.volatile
 * 2.atomicBoolean
 * 3.interrupt
 * @author zhaoyuan
 * @date 2023/3/14
 */
public class InterruptDemo {

    private static volatile boolean isInterruptVolatile = false;

    private static volatile AtomicBoolean isInterruptAtomic = new AtomicBoolean(false);

    public static void main(String[] args) {
//        m1_volatile();
//        m2_atomicboolean();
        m3_interrupt();

    }

    private static void m3_interrupt() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " is interrupt ");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " yayayaya");
            }
        });

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            t1.interrupt();
        }).start();
    }

    private static void m2_atomicboolean() {
        new Thread(()->{
            while(true){
                if (isInterruptAtomic.get()){
                    System.out.println(Thread.currentThread().getName()+" is interrupt ");
                    break;
                }
                System.out.println(Thread.currentThread().getName()+" yayayaya");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            isInterruptAtomic.set(true);
        }).start();
    }

    private static void m1_volatile() {
        new Thread(()->{
            while(true){
                if (isInterruptVolatile){
                    System.out.println(Thread.currentThread().getName()+" is interrupt ");
                    break;
                }
                System.out.println(Thread.currentThread().getName()+" yayayaya");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            isInterruptVolatile = true;
        }).start();
    }

}
