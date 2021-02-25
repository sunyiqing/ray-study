package com.ray.j2se.thread.test.semaphore.test3;



public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA[] a = new ThreadA[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = new ThreadA(service);
            a[i].start();
        }
    }
}
