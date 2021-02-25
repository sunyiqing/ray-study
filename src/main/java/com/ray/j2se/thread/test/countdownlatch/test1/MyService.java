package com.ray.j2se.thread.test.countdownlatch.test1;

import java.util.concurrent.CountDownLatch;

public class MyService {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void testMethod(){

        try {
            System.out.println("A");
            countDownLatch.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downMethod(){
        System.out.println("x");
        countDownLatch.countDown();
    }
}
