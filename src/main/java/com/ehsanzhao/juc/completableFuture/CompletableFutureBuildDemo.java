package com.ehsanzhao.juc.completableFuture;

import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2023/3/2
 */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //不指定线程池的话默认ForkJoinPool
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        //Runable
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName()+"----do something");
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },threadPool);
        //有返回值
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"----do something");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        },threadPool);

        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }

}
