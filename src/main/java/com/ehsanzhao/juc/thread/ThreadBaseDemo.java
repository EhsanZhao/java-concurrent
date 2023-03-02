package com.ehsanzhao.juc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaoyuan
 * @date 2023/3/1
 */
public class ThreadBaseDemo {
    static int i = 0;
    public static void main(String[] args) {
        testThread();
    }

    public synchronized static void testThread(){
        for (int i1 = 0; i1 < 50; i1++) {
            new Thread(()->{

                for (int i2 = 0; i2 < 100; i2++) {
                    i++;
                }
                System.out.println(Thread.currentThread().getName()+"-"+i);
            },"ehsanzhao-thread-"+(i1+1)).start();
        }
        System.out.println(Thread.currentThread().getName()+"-"+i);
    }

}
