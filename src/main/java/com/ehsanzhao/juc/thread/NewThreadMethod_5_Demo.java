package com.ehsanzhao.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 创建Thread的五种方法
 * @author zhaoyuan
 * @date 2023/3/2
 */
public class NewThreadMethod_5_Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"---启动");
        new MyThread().start();
        new Thread(new MyThreadRunable(),"method-02").start();
        FutureTask<Object> objectFutureTask = new FutureTask<>(new MyThreadCallable());
        new Thread(objectFutureTask,"method-03").start();
        System.out.println(Thread.currentThread().getName()+"---结束");
        System.out.println(Thread.currentThread().getName()+"---futureTask返回值："+objectFutureTask.get());
    }

}
//方式一 继承Thread
class MyThread extends Thread{
    @Override
    public void run() {
        super.setName("method-01");
        System.out.println(Thread.currentThread().getName()+"---启动");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"---结束");
    }
}
//方式二 实现Runable
class MyThreadRunable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---启动");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"---结束");
    }
}
//方式二 实现Callable 有返回值，且会抛出异常
class MyThreadCallable implements Callable<Object>{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---启动");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"---结束");
        return "callable";
    }
}
