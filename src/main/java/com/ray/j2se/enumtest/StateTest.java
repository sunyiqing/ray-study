package com.ray.j2se.enumtest;

import com.ray.j2se.enumtest.State;

/**
 * Created by yiqing on 2017/9/16.
 */
public class StateTest {
    public static void main(String[] args) {
        for (State day:State.values()){
            System.out.println("day declaringClass:"+day.getDeclaringClass().getName());
            System.out.println("day to string:"+day.toString());
            System.out.println("day ordinal:"+day.ordinal());
            System.out.println("day name:"+day.name());
            System.out.println(day);
        }
    }
}
