package com.ray.thread.synchronized1;

/**
 *  验证 synchronized 锁住一个字符串的情况下 其他线程是否会阻塞
 */
public class Test1 {

    public static void main(String[] args) {

        String a = "123";
        new Thread(() -> {
            synchronized (a.intern()){
                while (true){
                    System.out.println("111");
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (a.intern()){
                System.out.println("444");
            }

        }).start();
    }
}
