package com.ray.gof.Strategy.v2;

/**
 * Created by yiqing on 2017/5/28.
 */
public class FlyRoketPowered implements FlyBehavior {
    public void fly() {
        System.out.println("火箭飞");
    }
}
