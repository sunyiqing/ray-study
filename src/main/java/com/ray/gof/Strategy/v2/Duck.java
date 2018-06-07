package com.ray.gof.Strategy.v2;



/**
 * Created by yiqing on 2017/5/28.
 */
public abstract class Duck {
    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;
    //---动态的设置鸭子的技能
    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior=quackBehavior;
    }
    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior=flyBehavior;
    }
    public void performQuack(){
        quackBehavior.quack();
    }
    public void perforFly(){
        flyBehavior.fly();
    }
    //---动态的设置鸭子的技能
    public void swim(){
        System.out.println("所有鸭子都会游泳");
    }
    public abstract void display();



}
