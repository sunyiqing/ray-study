package com.ray.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lockTest();
    }

    /**
     * 模拟三个人去银行取钱
     * 柜台服务:相当于lock锁
     * 用户A、用户B、用户C：相当于三个线程
     */
    public static void lockTest(){

        new Thread(() ->{
            lock.lock();
            System.out.println("用户A,开始业务");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        },"A").start();


        new Thread(() ->{
            lock.lock();
            System.out.println("用户B,开始业务");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
        },"B").start();


        new Thread(() -> {
            lock.lock();
            System.out.println("用户C,开始业务");
            lock.unlock();
        },"C").start();
    }

}
