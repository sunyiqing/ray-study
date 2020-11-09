package com.ray.thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class NewThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new Thread1());
        t1.start();

        Thread t2 = new Thread(new Thread2());
        t2.start();

        FutureTask<String> futureTask = new FutureTask<>(new Thread3());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }


    static class Thread1 implements Runnable{

        @Override
        public void run() {
            System.out.println(1);
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println(2);
        }
    }

    static class Thread3 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "3";
        }
    }



}
