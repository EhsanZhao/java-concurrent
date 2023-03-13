package com.ehsanzhao.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyuan
 * @date 2023/3/13
 */

class Ticket{

    private static int num = 50;

    private ReentrantLock lock = new ReentrantLock(true); // true 公平锁，按照申请锁的顺序获取锁，false 非公平锁，默认非公平锁，不按照申请的顺序

    public void sale(){
        lock.lock();
        try{
            if(num>0){
                System.out.println(Thread.currentThread().getName()+" 卖出第"+(num--)+"张，还剩"+num);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

}

public class SaleTicketDemo {

    public static void main(String[] args) {



            new Thread(()-> {
                for (int i = 0; i < 55; i++) {
                    new Ticket().sale();
                }
            }
            ).start();

        new Thread(()-> {
            for (int i = 0; i < 55; i++) {
                new Ticket().sale();
            }
        }
        ).start();

        new Thread(()-> {
            for (int i = 0; i < 55; i++) {
                new Ticket().sale();
            }
        }
        ).start();
        
    }

}
