package com.ray.j2se.thread.test.semaphore.test1;

import java.util.concurrent.Semaphore;

public class Service {
    private Semaphore semaphore = new Semaphore(1);

    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + "当前时间:"+System.currentTimeMillis());
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "结束时间:"+System.currentTimeMillis());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
