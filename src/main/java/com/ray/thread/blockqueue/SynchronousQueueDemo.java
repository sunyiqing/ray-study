package com.ray.thread.blockqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiqing on 2021/3/6.
 */
public class SynchronousQueueDemo {

    /**
     * 线程AAA，入队列数组
     * 线程BBB,出队列数组
     * AAA入一个，BBB5秒后出一个，AAA才能入下一个
     * @param args
     */
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 插入 " + "1");

                synchronousQueue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 插入 " + "2");

                synchronousQueue.put("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 插入 " + "3");

                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 出队列 " +synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 出队列 " +synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 出队列 " +synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB").start();
    }
}
