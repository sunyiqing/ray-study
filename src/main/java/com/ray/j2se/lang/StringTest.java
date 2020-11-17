package com.ray.j2se.lang;

/**
 * 测试string 相关内容
 */
public class StringTest {

    public static void main(String[] args) {
        String st1 = "abc";//常量池中的对象
        String st2 = "abc";//常量池中的对象
        System.out.println(st1.equals(st2));
        System.out.println(st1 == st2);

        String st3 = new String("abc");// 堆内存的地址值
        System.out.println(st1 == st3);
        System.out.println(st1.equals(st3));



    }
}
