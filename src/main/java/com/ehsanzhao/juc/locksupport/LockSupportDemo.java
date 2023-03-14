package com.ehsanzhao.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程等待和唤醒的三种方式
 * 1。synchronized wait notify
 * 2. lock await signal
 * 3。LockSupport park unpark
 *
 * 1,2必须在锁块中，且必须先等待在唤醒，如果先唤醒后等待就会一直等待
 * 3 无需锁块，LockSupport会自动创建锁，但是  通行证上限只有一个  ，
 *      LockSupport.unpark(t1);多次也只会有一个通行证，一旦程序中有多个LockSupport.park();就会阻塞，必须成双成对
 * @author zhaoyuan
 * @date 2023/3/14
 */
public class LockSupportDemo {

    public static void main(String[] args) {

//        synchronizedWaitNotify();
//        lockAwaitSignal();
        lockSupportParkUnpark();
    }

    private static void lockSupportParkUnpark() {

        Thread t1 = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(Thread.currentThread().getName() + "---come in");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---被唤醒");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "---come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---被唤醒");
        }, "t2");
        t2.start();

        new Thread(()->{
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t2);
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName()+"---lalala--"+i);
            }
        },"t1").start();
    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println(Thread.currentThread().getName()+"---come in");
            lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"---被唤醒");
        },"t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"---come in");
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"---被唤醒");
        },"t2").start();

        new Thread(()->{
            lock.lock();
            condition.signal();
            condition.signal();
            lock.unlock();
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName()+"---lalala--"+i);
            }
        },"t1").start();
    }

    private static void synchronizedWaitNotify() {
        Object obj = new Object();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println(Thread.currentThread().getName()+"---come in");
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"---被唤醒");
        },"t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"---come in");
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"---被唤醒");
        },"t2").start();

        new Thread(()->{
            synchronized (obj){
                obj.notify();
                obj.notify();
            }
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName()+"---lalala--"+i);
            }
        },"t1").start();
    }

}
