package com.ray.j2se.thread.Synchronized;

public class SynchronizedDemo {
}

class TestNotStatic{
    public static volatile int race=0;
    public synchronized void increase(){
        race++;
    }
}

/**
 * synchronized是对类的当前实例进行加锁,单示例可以得到预期结果。
 */
class NotStaticTest1{
    public static void main(String[] args) {
        final TestNotStatic test = new TestNotStatic();
        Thread[] threads = new Thread[20];
        for(int i=0 ; i<20 ;i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0 ; j<100000 ; j++) {
                    test.increase();
                }
            });
            threads[i].start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(TestNotStatic.race);
    }
}

/**
 * synchronizedd多实例情况下不会得到预期结果
 */
class NotStaticTest2 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for(int i=0 ; i<20 ;i++) {
            threads[i] = new Thread(new Runnable(){
                TestNotStatic test = new TestNotStatic();
                @Override
                public void run() {
                    for(int j = 0 ; j<100000 ; j++) {
                        test.increase();
                    }
                }
            });
            threads[i].start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(TestNotStatic.race);
    }
}

class TestStatic {
    public static volatile int race = 0;
    public static synchronized void increase() {
        race ++ ;
    }
}

/**
 * 那么static synchronized恰好就是要控制类的所有实例的访问了，
 * synchronized static cSync{}防止多个线程同时访问这个类中的synchronized static 方法。它可以对类的所有对象实例起作用。
 */
class StaticTest {
    public static void main(String[] args) {
//      final TestStatic test = new TestStatic();
        Thread[] threads = new Thread[20];
        for(int i=0 ; i<20 ;i++) {
            threads[i] = new Thread(new Runnable(){
                TestStatic test = new TestStatic();
                @Override
                public void run() {
                    for(int j = 0 ; j<100000 ; j++) {
                        test.increase();
                    }
                }
            });
            threads[i].start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(TestStatic.race);
    }
}