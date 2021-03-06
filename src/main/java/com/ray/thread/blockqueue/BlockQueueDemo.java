package com.ray.thread.blockqueue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiqing on 2021/3/6.
 */
public class BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //异常组，add 添加队里元素，remove 移除队列元素 ,element 查看队列元素第一个元素
        ArrayBlockingQueue arrayBlockingQueue1 = new ArrayBlockingQueue(3);
        arrayBlockingQueue1.add("A");
        arrayBlockingQueue1.add("B");
        arrayBlockingQueue1.add("C");
        //TODO:多添加就会 Exception in thread "main" java.lang.IllegalStateException: Queue full
//        arrayBlockingQueue1.add("D");

        arrayBlockingQueue1.remove();
        arrayBlockingQueue1.remove();
//        arrayBlockingQueue1.remove();
        //TODO:多删除就会 Exception in thread "main" java.util.NoSuchElementException
//        arrayBlockingQueue1.remove();
        //TODO:如果队里为空，element 就会Exception in thread "main" java.util.NoSuchElementException
        System.out.println(arrayBlockingQueue1.element());


        //特殊值，插入方法成功true,失败false,移除方法成功返回出队元素，队列里没有元素返回null
        ArrayBlockingQueue arrayBlockingQueue2 = new ArrayBlockingQueue(3);
        arrayBlockingQueue2.offer("A");
        arrayBlockingQueue2.offer("B");
        //TODO:可以添加成功返回true
        System.out.println(arrayBlockingQueue2.offer("C"));
        //TODO:再添加添加不进去返回false
        System.out.println(arrayBlockingQueue2.offer("D"));
        System.out.println(arrayBlockingQueue2.poll());
        arrayBlockingQueue2.poll();
        arrayBlockingQueue2.poll();
        //TODO:这里会返回null
        System.out.println(arrayBlockingQueue2.poll());
        //TODO:查看元素，如果有元素返回值，如果无返回null
        System.out.println(arrayBlockingQueue2.peek());


        //阻塞，满时阻塞，出队列时为空阻塞
        ArrayBlockingQueue arrayBlockingQueue3 = new ArrayBlockingQueue(3);
        arrayBlockingQueue3.put("A");
        arrayBlockingQueue3.put("B");
        arrayBlockingQueue3.put("C");
        //TODO:这里将进行阻塞
//        arrayBlockingQueue3.put("D");
        arrayBlockingQueue3.take();
        arrayBlockingQueue3.take();
        arrayBlockingQueue3.take();
        //TODO:这里将进行阻塞
//        arrayBlockingQueue3.take();


        //超时，插入超过多久没有插入就返回false,
        ArrayBlockingQueue arrayBlockingQueue4 = new ArrayBlockingQueue(3);
        System.out.println(arrayBlockingQueue2.offer("A", 1, TimeUnit.MILLISECONDS));
        System.out.println(arrayBlockingQueue2.offer("A", 1, TimeUnit.MILLISECONDS));

        System.out.println(arrayBlockingQueue2.offer("A", 1, TimeUnit.MILLISECONDS));
        //TODO:这里插入等5秒，否则就返回false
        System.out.println(arrayBlockingQueue2.offer("A", 5, TimeUnit.MILLISECONDS));
        System.out.println(arrayBlockingQueue2.poll(1, TimeUnit.MILLISECONDS));
        System.out.println(arrayBlockingQueue2.poll(1, TimeUnit.MILLISECONDS));
        System.out.println(arrayBlockingQueue2.poll(1, TimeUnit.MILLISECONDS));
        //TODO:这里移除元素等5秒，否则就返回null
        System.out.println(arrayBlockingQueue2.poll(5, TimeUnit.MILLISECONDS));

    }
}
