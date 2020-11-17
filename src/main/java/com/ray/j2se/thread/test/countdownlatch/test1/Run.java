package com.ray.j2se.thread.test.countdownlatch.test1;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread thread = new MyThread(myService);
        thread.start();
        Thread.sleep(2000);
        myService.downMethod();
    }
}
