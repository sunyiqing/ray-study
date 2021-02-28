package com.ray.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiqing on 2021/2/27.
 */
public class CountDownLatchDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    //todo:这里变成3，主线程将阻塞
//    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    /**
     * A线程，调用商品系统
     * B线程，调用价格系统
     * 都完成才能继续下面的逻辑
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            System.out.println("A调用商品系统开始执行...");
            countDownLatch.countDown();
            System.out.println("A调用商品系统结束...");

        },"A").start();

        new Thread(() ->{
            System.out.println("B调用价格系统开始执行...");
            try {
                TimeUnit.MINUTES.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("B调用价格系统结束...");
        },"B").start();

        countDownLatch.await();
        System.out.println("开始聚合逻辑.......");
    }
}
