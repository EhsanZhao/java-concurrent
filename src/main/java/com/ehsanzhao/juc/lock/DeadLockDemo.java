package com.ehsanzhao.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁
 * 如何检测死锁
 * 1。jps -l ---> jstack pid
 * 2。jconsole
 * @author zhaoyuan
 * @date 2023/3/13
 */
public class DeadLockDemo {

    static final Object object1 = new Object();
    static final Object object2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (object1){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "object1 lock");
                synchronized (object2){
                    System.out.println(Thread.currentThread().getName() + "object2 lock");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (object2){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "object2 lock");
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName() + "object1 lock");
                }
            }
        },"t2").start();
    }


}
