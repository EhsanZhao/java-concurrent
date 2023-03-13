package com.ehsanzhao.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 题目：谈谈你对多线程锁的理解，8锁案例说明
 * 1。先打印短信还是邮件
 * 2。停4秒在短信方法内，先打印短信还是邮件
 * 3。新增普通的hello方法，是先打短信还是hello
 * 4。现在有两部手机，先打印短信还是邮件
 * 5。两个静态同步方法，1部手机，先打印短信还是邮件
 * 6。两个静态同步方法，2部手机，先打印短信还是邮件
 * 7。1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
 * 8。1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
 * @author zhaoyuan
 * @date 2023/3/13
 */
class Phone{

    public synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---->sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println("---->sendSMS");
    }

    public void hello(){
        System.out.println("---->sendMail");
    }
}
public class Lock8Demo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        new Thread(()->{
            phone.sendSMS();
//            phone.hello();
//            phone2.sendSMS();
        },"b").start();
    }

}
