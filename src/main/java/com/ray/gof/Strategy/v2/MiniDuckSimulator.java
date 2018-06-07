package com.ray.gof.Strategy.v2;



/**
 * Created by yiqing on 2017/5/28.
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck duck=new MallardDuck();

        //原来的飞行
        duck.setFlyBehavior(new FlyWithWings());
        duck.perforFly();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();

        //动态添加火箭飞
        duck.setFlyBehavior(new FlyRoketPowered());
        duck.perforFly();

        duck.display();

    }
}
