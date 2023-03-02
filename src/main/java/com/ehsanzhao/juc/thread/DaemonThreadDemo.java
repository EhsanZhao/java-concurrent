package com.ehsanzhao.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyuan
 * @date 2023/3/1
 */
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon()?"守护线程":"用户线程");
            while (true){

            }
        });
        //设置守护线程
//        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //非守护线程，主线程结束时，用户线程一直会运行直到结束
        System.out.println(Thread.currentThread().getName()+"=====end");
    }

}
