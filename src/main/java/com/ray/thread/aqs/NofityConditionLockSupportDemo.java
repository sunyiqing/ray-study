package com.ray.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiqing on 2021/2/25.
 */
public class NofityConditionLockSupportDemo {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        conditionAWaitSignal();
//        parkUnPark();
    }

    private static void parkUnPark(){
        Thread a = new Thread(() ->{
            //TODO:打开这里，不影响
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "----come in");
            //TODO:只有park，没有unpark,会一直等待。
            System.out.println(Thread.currentThread().getName() + "----等待中");
            LockSupport.park();
            //TODO:多个unpark不会让permit继续+1,只能继续等待
//            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + "----开始执行");
        },"A");
        a.start();

        Thread b = new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "---唤醒");
            LockSupport.unpark(a);
            //TODO:多个unpark不会让permit继续+1,只能继续等待
//            LockSupport.unpark(a);

        },"B");
        b.start();
    }

    private static void conditionAWaitSignal(){
        new Thread(() ->{
            //TODO:打开这里，将一直等待中
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO:去掉 lock java.lang.IllegalMonitorStateException
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "----come in");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "----等待中");
            lock.unlock();
        },"A").start();

        new Thread(() ->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "---唤醒");
            condition.signal();
            lock.unlock();
        },"B").start();
    }


    private static void synchronizedWaitNotify(){
        new Thread(() ->{
            //TODO:打开这里，将一直等待中
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TODO:去掉 synchronized java.lang.IllegalMonitorStateException
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + "----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "----等待中");

            }
        },"A").start();

        new Thread(() ->{
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + "---唤醒");
                objectLock.notify();
            }
        },"B").start();
    }
}
