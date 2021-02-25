package com.ray.j2se.thread.test.semaphore.test3;

import java.util.concurrent.Semaphore;

public class Service {
    private Semaphore semaphore = new Semaphore(10);

    public void testMethod(){
        try {
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + "当前时间:"+System.currentTimeMillis());
            int sleepValue = (int)Math.random() * 1000000;
            System.out.println(Thread.currentThread().getName() + "停止了:"+sleepValue/1000);

            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + "结束时间:"+System.currentTimeMillis());
            semaphore.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
