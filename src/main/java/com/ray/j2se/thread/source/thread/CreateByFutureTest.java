package com.ray.j2se.thread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 带返回值的两种方式
 */
public class CreateByFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        FutureTask<String> futureTask=new FutureTask<String>(new Task2());
        Thread thread=new Thread(futureTask);
        thread.start();
        System.out.println("输出返回内容"+futureTask.get());

        //这里的构造函数的第二个参数可以是返回值详见，源代码
        FutureTask<String> futureTask1=new FutureTask<String>(new Task3(),"baage");
//        FutureTask<String> futureTask1=new FutureTask<String>(new Task3(),null);
        Thread thread1=new Thread(futureTask1);
        thread1.start();
        System.out.println("输出返回内容"+futureTask1.get());
    }
}

class Task2 implements Callable<String>{

    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "just sout -3";
    }
}

class Task3 implements Runnable{

    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("just sout -4");
    }
}