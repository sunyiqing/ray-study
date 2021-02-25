package com.ray.j2se.thread.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> linkedBlockingQueue=new LinkedBlockingQueue<>();
        linkedBlockingQueue.offer("java");
        linkedBlockingQueue.offer("php");
        linkedBlockingQueue.offer("py");
        linkedBlockingQueue.offer("c#");

        //取出第一个元素,但不删除
        String headNode=linkedBlockingQueue.peek();
        System.out.println(headNode);
        linkedBlockingQueue.forEach(l-> System.out.println(l));

        //取出第一个元素,并删除
        String headNode2=linkedBlockingQueue.poll();
        System.out.println(headNode2);
        linkedBlockingQueue.forEach(l-> System.out.println(l));

        //加入到最后
        LinkedBlockingQueue<String> linkedBlockingQueueNew=new LinkedBlockingQueue<>();
        linkedBlockingQueueNew.offer("c++");
        System.out.println(linkedBlockingQueue.drainTo(linkedBlockingQueueNew));
        linkedBlockingQueueNew.forEach(l-> System.out.println(l));

        //清除
        linkedBlockingQueue.clear();
        linkedBlockingQueue.forEach(l-> System.out.println(l));
    }
}
