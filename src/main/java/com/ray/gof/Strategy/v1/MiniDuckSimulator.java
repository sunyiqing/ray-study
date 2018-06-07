package com.ray.gof.Strategy.v1;

/**
 * Created by yiqing on 2017/5/28.
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck duck=new MallardDuck();
        duck.performFly();
        duck.performQuack();
        duck.display();
    }
}
