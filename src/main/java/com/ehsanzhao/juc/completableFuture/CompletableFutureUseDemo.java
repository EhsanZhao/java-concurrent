package com.ehsanzhao.juc.completableFuture;

import java.util.concurrent.*;

/**
 * @author zhaoyuan
 * @date 2023/3/2
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //默认线程池类似守护线程，主线程结束默认线程池自动关闭
        CompletableFuture.supplyAsync(()->{
            int random = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread().getName()+"----do something:"+random);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(random>5){
                random = random/0;
            }
            return "hello supplyAsync";
        },threadPool)
                .whenComplete((v,e)->{
                    if(e == null){
                        System.out.println(Thread.currentThread().getName()+"----result:"+v);
                    }
                })
                .exceptionally(e->{
                    e.printStackTrace();
                    return null;
                });
        System.out.println(Thread.currentThread().getName()+"----do other things");
        threadPool.shutdown();
    }

}
