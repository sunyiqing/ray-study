package com.ray.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiqing on 2021/3/6.
 */

/**
 * 线程A，负责通知C1唤醒
 * 线程B，负责C1阻塞
 * 线程A休眠5秒钟，线程B先执行
 *
 * 问题，线程B已经发生了lock操作，c1阻塞后。线程A是否还能进入呢执行呢？
 *
 */
public class ConditionDemo {
    private static Lock lock = new ReentrantLock();

     static Condition c1 = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入线程A执行");
            c1.signal();
            System.out.println(Thread.currentThread().getName() + "通知线程B开始执行");
            lock.unlock();
        },"A").start();

        new Thread(() ->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入线程B执行");
            try {
                System.out.println(Thread.currentThread().getName() + "线程B进入阻塞");
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程B阻塞释放");
            lock.unlock();
        },"B").start();


    }
}
