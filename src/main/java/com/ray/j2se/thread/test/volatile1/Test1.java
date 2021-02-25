package com.ray.j2se.thread.test.volatile1;

import java.util.concurrent.TimeUnit;

/**
 *  简单 演示 volatile 作用
 *  1.使用 volatile 时当 running = false,m方法立即结束
 *  2.不使用volatile 时当 running = false,m方法不会立即结束
 */
public class Test1 {
    volatile boolean running = true;
//    boolean running = true;
    void m(){
        System.out.println("start");
        while (running){
            //TODO:
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        new Thread(test1::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.running = false;
        System.out.println("test1.running = false;");
    }

}
