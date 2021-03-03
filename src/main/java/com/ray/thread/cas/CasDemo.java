package com.ray.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yiqing on 2021/3/3.
 */
public class CasDemo {

    public static void main(String[] args) {
        helloCas();
    }

    /***
     * cas 的第一个例子
     * true,current data:2019
     * false,current data:2019
     */
    private static void helloCas(){
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + ",current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014) + ",current data:" + atomicInteger.get());
    }
}
