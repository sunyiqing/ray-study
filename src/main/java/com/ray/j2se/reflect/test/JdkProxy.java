package com.ray.j2se.reflect.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yiqing on 2018/6/2.
 */
public class JdkProxy implements InvocationHandler {

    private MyInterface myClass;


    public MyInterface newPorxyInstance(MyInterface myClass){
        this.myClass=myClass;
        ClassLoader clazzLoader=myClass.getClass().getClassLoader();
        Class[] classes=myClass.getClass().getInterfaces();
        return (MyInterface) Proxy.newProxyInstance(clazzLoader,classes,this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("fn")) {
            System.out.println("拦截住");
        }
        method.invoke(myClass, args);
        return null;
    }

    public static void main(String[] args) {
        //创建被代理的对象
        MyInterface m=new MyClass();
        //创建代理对象
        MyInterface mm=new JdkProxy().newPorxyInstance(m);
        //执行方法时，用代理对象执行。
        mm.fn();

    }
}

interface MyInterface{
    public void fn();
    public void fn1();
}

class MyClass implements MyInterface{

    public void fn() {
        System.out.println("fn");
    }

    public void fn1() {
        System.out.println("fn1");
    }
}
