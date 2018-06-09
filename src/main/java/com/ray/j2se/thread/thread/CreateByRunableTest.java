package com.ray.j2se.thread.thread;

/**
 * 使用Runnable 创建线程
 */
public class CreateByRunableTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread=new Thread(new Task());
        thread.start();
    }

}

class Task implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("just sout -2");
    }
}
