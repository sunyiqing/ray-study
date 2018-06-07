package com.ray.j2se.thread.threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yiqing on 2018/5/13.
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(new Task()).start();
        }
//        new ExecutorsTest().newSingleThreadExecutorTest();

    }

    /**
     * 固定大小的线程池
     */

    private void newFixedThreadExecutorsTest(){
        ExecutorService fixedThread=Executors.newFixedThreadPool(10);
        fixedThread.submit(new Task());
    }

    /**
     * Executor ,单线程线程池
     */
    private void newSingleThreadExecutorTest(){
        ExecutorService  singleThread=Executors.newSingleThreadExecutor();
        try {
            singleThread.submit(new Task());
            singleThread.shutdown();
            singleThread.awaitTermination(5, TimeUnit.HOURS);
        }catch (Exception e){

        };

    }

    static class Task implements Runnable{
        private  volatile  int i;
        public int getI(){
            return i;
        }
        public void run() {
            try {
                Random random=new Random();
                System.out.println(random.nextInt());
                System.out.println(i++);
            }catch (Exception e){

            }
        }
    }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    private static final int RUNNING    = -1 << (Integer.SIZE - 3);
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static int workerCountOf(int c)  { return c & (1 << COUNT_BITS) - 1; }

    private void retryTest(){
        int count = 0;
        retry:
        for (; ; ) {
            count = 0;
            for (; ; ) {
                count++;
                System.out.println("count==" + count);
                if (count % 5 == 0) {
                    continue retry;
                }
            }
        }
    }
    //-------------------------------------------------------------------------------
}
