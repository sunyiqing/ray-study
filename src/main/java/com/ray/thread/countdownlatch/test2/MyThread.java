package com.ray.thread.countdownlatch.test2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class MyThread extends Thread {
    private CountDownLatch countDownLatch;;

    public MyThread(CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            countDownLatch.countDown();
//            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
