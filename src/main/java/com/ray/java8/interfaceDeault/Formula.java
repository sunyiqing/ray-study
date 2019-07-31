package com.ray.java8.interfaceDeault;

/**
 * 接口中实现默认的方法
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }


    int compareMax(int a,int b);

    default int max(int a,int b){
        return Math.max(a,b);
    }
}
