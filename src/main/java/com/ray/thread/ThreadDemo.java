package com.ray.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yiqing on 2021/3/8.
 */
public class ThreadDemo {
    static class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println(1);
        }
    }

    static class Thread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "3";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // thread
        Thread thread = new Thread(new Thread1());
        thread.start();

        //Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        }).start();

        //FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new Thread3());
        Thread t = new Thread(futureTask);
        t.start();
        System.out.println(futureTask.get());

    }
}
