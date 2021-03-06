package com.ray.thread.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiqing on 2021/3/6.
 */

/**
 * 主要需求
 *
 * 两个线程，
 * 线程AAA,循环五次。
 * 线程BBB,循环五次
 * 保证线程AAA和线程BBB能交替的输出0 1。
 */
class ShareDate{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 加1操作
     */
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 0){
                //等待，不能生成
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "number:" +number);
            //唤醒通知
            condition.signalAll();

        }finally {
            lock.unlock();
        }
    }

    /**
     * 减1操作
     * @throws InterruptedException
     */
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "number:" +number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareDate.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareDate.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }




}
