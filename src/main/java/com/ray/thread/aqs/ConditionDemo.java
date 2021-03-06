package com.ray.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiqing on 2021/3/6.
 */

/*
 * 线程A，后执行，负责通过Condition.signal唤醒线程B
 * 线程B，先执行，调用Condition.await阻塞
 * 问题，线程B已经发生了lock操作，阻塞后。线程A是否还能进入执行呢？
 *
 */
public class ConditionDemo {
    private static Lock lock = new ReentrantLock();

     static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入线程A执行");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "通知线程B开始执行");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "通知线程B结束");

        },"A").start();

        new Thread(() ->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入线程B执行");
            try {
                System.out.println(Thread.currentThread().getName() + "线程B进入阻塞");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程B阻塞释放");
            lock.unlock();
        },"B").start();


    }
}
