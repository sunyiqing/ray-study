package com.ray.thread.threadpool;

import java.util.concurrent.*;

/**
 * Created by yiqing on 2021/3/10.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        executorsThreadPool();
//        bankThreadPool();
//        abortPolicyRejectedThreadPool();
//        callerRunsRejectedThreadPool();
//        discardOldestThreadPool();
        discardRejectedThreadPool();
    }

    /**
     * 会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去
     */
    private static void discardOldestThreadPool(){

        ThreadPoolExecutor bankThreadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.HOURS,
                        new ArrayBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
        try{
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                bankThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + finalI +"号办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bankThreadPool.shutdown();
        }
    }
    /**
     * 会让被线程池拒绝的任务直接抛弃，不会抛异常也不会执行。
     */
    private static void discardRejectedThreadPool(){

        ThreadPoolExecutor bankThreadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.HOURS,
                        new ArrayBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        try{
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                bankThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + finalI +"号办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bankThreadPool.shutdown();
        }
    }

    /**
     * 主线程处理，会用调用execute函数的上层线程去执行被拒绝的任务。
     */
    private static void callerRunsRejectedThreadPool(){

        ThreadPoolExecutor bankThreadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.HOURS,
                        new ArrayBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        try{
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                bankThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + finalI +"号办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bankThreadPool.shutdown();
        }
    }

    /**
     * 直接抛出异常
     */
    private static void abortPolicyRejectedThreadPool(){

        ThreadPoolExecutor bankThreadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.HOURS,
                        new ArrayBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        try{
            for (int i = 1; i <= 9; i++) {
                int finalI = i;
                bankThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + finalI +"号办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bankThreadPool.shutdown();
        }
    }
    /**
     * 银行办理业务场景
     * 2个柜台
     * 3个临时柜台
     * 3个候客区
     * TODO:验证：6、7、8号客人在临时柜台办理业务
     */
    private static void bankThreadPool(){
        ThreadPoolExecutor bankThreadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.HOURS,
                        new ArrayBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        try{
            for (int i = 1; i <= 8; i++) {
                int finalI = i;
                bankThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t" + finalI +"号办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bankThreadPool.shutdown();
        }
    }
    /**
     * 三种常用的线程池方法
     */
    private static void executorsThreadPool(){
        //固定线程池的，一个银行有3个办理窗口，也就是有3个处理线程
        //输出的Thread.currentThread().getName() 是 1 - 3
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(3);
        try{
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                fixedExecutorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t"+ "小" + finalI +"办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fixedExecutorService.shutdown();
        }
        //一池单线程
        //输出的Thread.currentThread().getName() 是唯一的
        System.out.println("=========================");
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        try{
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                singleExecutorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t"+ "小" + finalI +"办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            singleExecutorService.shutdown();
        }
        //一池多线程
        //输出的Thread.currentThread().getName() 是多个
        System.out.println("=========================");
        ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                cachedExecutorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t"+ "小" + finalI +"办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cachedExecutorService.shutdown();
        }
    }
}
