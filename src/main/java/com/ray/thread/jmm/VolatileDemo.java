package com.ray.thread.jmm;

import java.util.concurrent.TimeUnit;

/**
 * 验证Volatile可见性
 * 线程A，将data修改为60
 * 主线程，data == 0将一直循环
 * 在线程A修改data后，看主线程的循环什么时候停止，验证Volatile可见性
 *
 */
public class VolatileDemo {

    static class AddData{
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

    public static void main(String[] args) {
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
