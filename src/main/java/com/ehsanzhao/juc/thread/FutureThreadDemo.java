package com.ehsanzhao.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoyuan
 * @date 2023/3/2
 */
public class FutureThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"---主线程开启");

        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName()+"---子线程开始");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"---子线程结束");
                return "hello";
            }
        } );
        System.out.println(Thread.currentThread().getName()+"---do something");
        System.out.println(Thread.currentThread().getName()+"---status isDone:"+futureTask.isDone());
        System.out.println(Thread.currentThread().getName()+"---status isCancelled:"+futureTask.isCancelled());
        new Thread(futureTask,"future-task").start();
        System.out.println(Thread.currentThread().getName()+"---status isDone:"+futureTask.isDone());
        System.out.println(Thread.currentThread().getName()+"---status isCancelled:"+futureTask.isCancelled());
//        System.out.println(futureTask.get()); //get会阻塞当前线程 不建议使用
        while(true){//轮询的方式获取返回值，但是会浪费无谓的cpu资源 比起阻塞建议使用轮询
            if(futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else{
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中");
            }
        }
        System.out.println(Thread.currentThread().getName()+"---status isDone:"+futureTask.isDone());
        System.out.println(Thread.currentThread().getName()+"---status isCancelled:"+futureTask.isCancelled());
        System.out.println(Thread.currentThread().getName()+"---主线程结束");
    }

}
