package com.ehsanzhao.juc.thread;

import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2023/3/2
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        FutureTask futureTask1 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return "t1";
        });
        executorService.submit(futureTask1);

        FutureTask futureTask2 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return "t2";
        });
        executorService.submit(futureTask2);

        FutureTask futureTask3 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return "t3";
        });
        executorService.submit(futureTask3);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
        executorService.shutdown();

    }

}
