package com.ray.thread.aqs;

/**
 * Created by yiqing on 2021/3/6.
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间顺序调用，实现A —> B -> C
 * A打印5次，B打印10次，C打印15次
 * 紧接着A打印5次，B打印10次，C打印15次
 * ...
 * 来10轮
 */

class ShareData{

    private int number = 1;
    private Lock lock = new ReentrantLock();

    public Condition c1 = lock.newCondition();
    public Condition c2 = lock.newCondition();
    public Condition c3 = lock.newCondition();

    public void printData(Integer curNum,Integer nextNum,Condition curContion,Condition nextContion,Integer printTime) throws InterruptedException {
        lock.lock();
        System.out.println("---------"+Thread.currentThread().getName());

        try{
            while (number != curNum){
                curContion.await();
            }
            for (int i = 1; i <= printTime; i++) {
                System.out.println(Thread.currentThread().getName() + "输出次数:" + i);
            }
            number = nextNum;
            nextContion.signal();

        }finally {
            lock.unlock();
        }
    }

//    public void printData5() throws InterruptedException {
//        lock.lock();
//        try{
//            while (number != 1){
//                c1.await();
//            }
//            for (int i = 1; i <= 5; i++) {
//                System.out.println(Thread.currentThread().getName() + "输出次数:" + i);
//            }
//            number = 2;
//            c2.signal();
//
//        }finally {
//            lock.unlock();
//        }
//    }
//    public void printData10() throws InterruptedException {
//        lock.lock();
//        try{
//            while (number != 2){
//                c2.await();
//            }
//            for (int i = 1; i <= 10; i++) {
//                System.out.println(Thread.currentThread().getName() + "输出次数:" + i);
//            }
//            number = 3;
//            c3.notify();
//
//        }finally {
//            lock.unlock();
//        }
//    }
//    public void printData15() throws InterruptedException {
//        lock.lock();
//        try{
//            while (number != 3){
//                c3.await();
//            }
//            for (int i = 1; i <= 15; i++) {
//                System.out.println(Thread.currentThread().getName() + "输出次数:" + i);
//            }
//            number = 1;
//            c1.notify();
//
//        }finally {
//            lock.unlock();
//        }
//    }

}

public class ConditionPrintData {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.printData(1,2,shareData.c1,shareData.c2,5);
//                    shareData.printData5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.printData(2,3,shareData.c2,shareData.c3,10);
//                    shareData.printData10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.printData(3,1,shareData.c3,shareData.c1,15);
                    System.out.println("==================================="+i);
//                    shareData.printData15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }

}
