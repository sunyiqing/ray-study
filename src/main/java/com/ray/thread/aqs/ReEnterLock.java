package com.ray.thread.aqs;

/**
 * Created by yiqing on 2021/2/25.
 */
public class ReEnterLock {

    private static Object objectA = new Object();
    private static Object object = new Object();

    public static void m(){
        new Thread(() ->{
            synchronized (objectA){
                System.out.println("---外层");
                synchronized (objectA){
                    System.out.println("---中层");
                    synchronized (object){
                        System.out.println("---内层");
                    }
                }
            }

        }).start();
    }
    public static void main(String[] args) {
        m();
    }
}
