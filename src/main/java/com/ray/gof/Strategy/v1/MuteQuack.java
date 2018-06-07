package com.ray.gof.Strategy.v1;

/**
 * Created by yiqing on 2017/5/28.
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<silence>");
    }
}
