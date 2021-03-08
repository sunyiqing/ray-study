package com.ray.thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A生产蛋糕
 * B消费蛋糕
 * volatile flag 为开关
 */
class Resource{
    private volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public Resource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void stop(){
        this.flag = false;
    }

    public void myProd() throws InterruptedException {
        String result = null;
        boolean offerFlag = false;
        while (flag){
            result = atomicInteger.getAndIncrement() + "";
            offerFlag = blockingQueue.offer(result,2L, TimeUnit.SECONDS);
            if (offerFlag){
                System.out.println(Thread.currentThread().getName() + "生成"+result+"蛋糕成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "生成"+result+"蛋糕成功");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "生成结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null == result){
                System.out.println(Thread.currentThread().getName() + "超过2秒钟没有取出蛋糕，消费退出");
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费"+result+"蛋糕");
        }
    }
}
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(10);
        Resource resource = new Resource(blockingQueue);
        new Thread(() ->{
            try {
                resource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() ->{
            try {
                resource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.stop();
    }
}
