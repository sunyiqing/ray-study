package com.ray.gof.Strategy.v1;

/**
 * 这个飞行给会飞的鸭子
 * Created by yiqing on 2017/5/28.
 */
public class FlyWithWIings implements FlyBehavior  {
    public void fly() {
        System.out.println("i am fly");
    }
}
