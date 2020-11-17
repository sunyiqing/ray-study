package com.ray.j2se.thread.reentrantLock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yiqing on 2018/5/27.
 */

public class ReentrantLockTest  {

    public static void main(String[] args) {
        Executor fixed=Executors.newFixedThreadPool(10);
        fixed.execute(new Runnable() {
            public void run() {
                int i1=1;
                int j1=2;
                Match.add(i1,j1);
            }
        });
    }

}
class Match{
    public static boolean test(){
        boolean interrupted = false;
        for (;;) {
            System.out.println(1);
            return interrupted;
        }
    }
    public static int add(int i,int j){
        Lock lock=new ReentrantLock();
        int result=0;
        try {
            lock.lock();
            result=i+j;
        }catch (Exception e){
            //// TODO: 
        }finally {
            if(lock!=null){
                lock.unlock();
            }
        }
        return result;
    }
    public int addMyself(int i){
        return i++;
    }
}
