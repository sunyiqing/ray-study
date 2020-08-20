package com.ray.thread.countdownlatch.test2;

import java.util.concurrent.CountDownLatch;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        MyThread[] myThread = new MyThread[(int)countDownLatch.getCount()];
        for (int i = 0; i < myThread.length; i++) {
            myThread[i] = new MyThread(countDownLatch);
            myThread[i].setName("线程"+i);
            myThread[i].start();
        }
        countDownLatch.await();
        System.out.println("都回来了");
    }
}
