package com.ray.j2se.thread.thread;

/**
 * 使用Thread 创建线程
 */
public class CreateByThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Task1 task=new Task1();
        task.start();
    }
}

class Task1 extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("just sout -2");
    }
}
