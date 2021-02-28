package com.ray.thread.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiqing on 2021/2/27.
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    //todo:这里变成3，主线程将阻塞
//    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    /**
     * A线程，小明从家里到公司
     * B线程，小王从家里到公司
     * 都到了公司以后，才能一起出发去旅游
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            System.out.println("A，小明从家里出发去公司...");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("A，小明到达公司...");

        },"A").start();

        new Thread(() ->{
            System.out.println("B，小王从家里出发去公司...");
//            try {
//                TimeUnit.MINUTES.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("B，小王到达公司...");
        },"B").start();



        System.out.println("大家一起出发去旅游.......");
    }
}
