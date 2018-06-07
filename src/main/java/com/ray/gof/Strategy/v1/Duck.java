package com.ray.gof.Strategy.v1;

/**
 * 鸭子超类
 * Created by yiqing on 2017/5/28.
 */
public abstract class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void performQuack(){
        quackBehavior.quack();
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public  void swin(){
        System.out.println("all ducks float,even decoys!");
    }

    public abstract void display();
}

