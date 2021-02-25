package com.ray.j2se.thread.queue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();
        synchronousQueue.offer("java");
        synchronousQueue.offer("c#");
        synchronousQueue.forEach(l-> System.out.println(l));

    }
}
