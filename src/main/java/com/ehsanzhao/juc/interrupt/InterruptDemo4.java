package com.ehsanzhao.juc.interrupt;

/**
 * Thread.interrupted()
 * 这个方法会做两件事
 * 1。返回当前线程的中断状态
 * 2。将当前线程的中断状态清零并重新设置为false，清除线程的中断状态
 *
 * @author zhaoyuan
 * @date 2023/3/14
 */
public class InterruptDemo4 {

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"--"+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+"--"+Thread.currentThread().isInterrupted());

            Thread.currentThread().interrupt();

            System.out.println(Thread.currentThread().getName()+"--"+ Thread.interrupted());
            System.out.println(Thread.currentThread().getName()+"--"+ Thread.interrupted());
        }).start();


    }

}
