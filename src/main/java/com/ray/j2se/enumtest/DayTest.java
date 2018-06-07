package com.ray.j2se.enumtest;

/**
 * Created by yiqing on 2017/9/16.
 */
public class DayTest {
    public static void main(String[] args) {
        for (Day day:Day.values()){
            System.out.println("day declaringClass:"+day.getDeclaringClass().getName());
            System.out.println("day to string:"+day.toString());
            System.out.println("day ordinal:"+day.ordinal());
            System.out.println("day name:"+day.name());
            System.out.println(day);

        }
    }
}
