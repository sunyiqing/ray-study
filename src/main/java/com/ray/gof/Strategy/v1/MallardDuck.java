package com.ray.gof.Strategy.v1;

/**
 * Created by yiqing on 2017/5/28.
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        flyBehavior=new FlyWithWIings();
        quackBehavior=new Quack();
    }

    public void display() {
        System.out.println("i am a real mallard duck");
    }
}
