package com.ray.j2se.thread.test.countdownlatch.test2;

import java.util.concurrent.CountDownLatch;

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
