package com.ray.thread.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class VolatileDemo {


    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
//        volatileDemo.kejianxing();

        volatileDemo.yuanzixing();
    }


    private void yuanzixing(){
        AddData1 addData = new AddData1();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    addData.addPlusPlus();
                    addData.addPlusPlusBySynchronized();
                    addData.addPlusPlusByCas();
                }
            },String.valueOf(i)).start();
        }


        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println("addData.getData():"+addData.getData());
        System.out.println("addData.getData1():"+addData.getData1());
        System.out.println("addData.atomicInteger:"+addData.atomicInteger);

    }
    /**
     * 验证Volatile可见性
     * 线程A，将data修改为60
     * 主线程，data == 0将一直循环
     * 在线程A修改data后，看主线程的循环什么时候停止，验证Volatile可见性
     *
     */
    private void kejianxing(){
        AddData addData = new AddData();
        new Thread(() -> {
            System.out.println("A线程开始修改数据");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addData.addData();

        },"A").start();

        while (addData.getData() == 0){
        }
        System.out.println("data被修改了="+ addData.getData());
    }


}


class AddData{
    //todo:打开这里主线程将一直执行
//        private Integer data;
    private volatile Integer data;
    AddData(){
        data = 0;
    }

    public void addData(){
        data = 60;
    }
    public Integer getData(){
        return data;
    }
}

class AddData1{
    private volatile Integer data;
    private volatile Integer data1;

    AddData1(){
        data = 0;
        data1 = 0;
    }

    /**
     * volatile 不能保证原子性
     */
    public void addPlusPlus(){
        data++;
    }

    /**
     * 保证原子性方法1 使用 synchronized
     */
    public synchronized void addPlusPlusBySynchronized(){
        data1++;
    }

    /**
     * 保证原子性方法2 使用CAS
     */
    public AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlusByCas(){
        atomicInteger.getAndIncrement();
    }

    public Integer getData() {
        return data;
    }

    public Integer getData1() {
        return data1;
    }


}
