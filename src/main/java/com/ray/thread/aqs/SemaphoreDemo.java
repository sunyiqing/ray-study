package com.ray.thread.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiqing on 2021/2/28.
 */
public class SemaphoreDemo {

    /**
     * 标识停车场只有两个停车位
     */
    private static Semaphore semaphore = new Semaphore(2);


    /**
     * A线程，汽车A来停车
     * B线程，汽车B来停车
     * C线程，汽车C来停车
     * 停车场只有两个停车位，汽车A先停车50分钟，汽车B后停车20秒，最后的汽车C来停车，需要等待，直到有车位才能停车。
     *
     *
     * 原理是：让后来的C车，加入双向链表中线程park，等待某一个汽车走后，会进行unpark操作

     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            System.out.println("A，汽车A来停车...");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A，汽车A进入停车场，开始停车中...");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
            System.out.println("A，汽车A离开停车场...");

        },"A").start();

        new Thread(() ->{
            System.out.println("B，汽车B进入停车场，开始停车中...");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B，汽车B在停车中...");

            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
            System.out.println("B，汽车B离开停车场...");
        },"B").start();


        new Thread(() ->{
            System.out.println("C，汽车C来停车...");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C，汽车C进入停车场，开始停车中...");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
            System.out.println("C，汽车C离开停车场...");
        },"C").start();


    }
}
