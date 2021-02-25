package com.ray.basic;

/**
 * Created by yiqing on 2020/11/5.
 */
public class HashCodeTest {


    public static void main(String[] args) {
        Object object = new Object();
        Object object2 = new Object();
        System.out.println(object.equals(object2));
        Integer i = 1;
        Integer j = 1;
        System.out.println(i.equals(j));
    }
}
