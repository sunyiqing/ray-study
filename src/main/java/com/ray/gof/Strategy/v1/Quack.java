package com.ray.gof.Strategy.v1;

/**
 * Created by yiqing on 2017/5/28.
 */
public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("i am quack");
    }
}
