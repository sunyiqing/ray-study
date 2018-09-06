package com.ray.connectionpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现原理
 *  数据库连接池在初始化时将创建一定数量的数据库连接放到连接池中，
 *  这些数据库连接的数量是由最小数据库连接数制约。无论这些数据库连接是否被使用，连接池都将一直保证至少拥有这么多的连接数量。
 *  连接池的最大数据库连接数量限定了这个连接池能占有的最大连接数，当应用程序向连接池请求的连接数超过最大连接数量时，
 *  这些请求将被加入到等待队列中。
 *  连接池基本的思想是在系统初始化的时候，将数据库连接作为对象存储在内存中，当用户需要访问数据库时，并非建立一个新的连接，而是从连接池中取出一个已建立的空闲连接对象。使用完毕后，用户也并非将连接关闭，而是将连接放回连接池中，以供下一个请求访问使用。而连接的建立、断开都由连接池自身来管理。同时，还可以通过设置连接池的参数来控制连接池中的初始连接数、连接的上下限数以及每个连接的最大使用次数、最大空闲时间等等。也可以通过其自身的管理机制来监视数据库连接的数量、使用情况等。
 * 注意事项
 1、数据库连接池的最小连接数是连接池一直保持的数据库连接，所以如果应用程序对数据库连接的使用量不大，将会有大量的数据库连接资源被浪费。
 2、数据库连接池的最大连接数是连接池能申请的最大连接数，如果数据库连接请求超过此数，
 后面的数据库连接请求将被加入到等待队列中，这会影响之后的数据库操作。
 3、最大连接数具体值要看系统的访问量.要经过不断测试取一个平衡值
 4、隔一段时间对连接池进行检测,发现小于最小连接数的则补充相应数量的新连接
 5、最小连接数与最大连接数差距，最小连接数与最大连接数相差太大，那么最先的连接请求将会获利，
 之后超过最小连接数量的连接请求等价于建立一个新的数据库连接。不过，这些大于最小连接数的数据库连接在使用完不会马上被释放
 它将被放到连接池中等待重复使用或是空闲超时后被释放。

 https://blog.csdn.net/fuyuwei2015/article/details/72419975
 *
 *
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);

    /**
     *    保证所有ConnectionRunner能够同时开始
     */
    static CountDownLatch start = new CountDownLatch(1);

    /**
     * main线程将会等待所有ConnectionRunner结束后才能继续执行
     */
    static CountDownLatch end;

    public static void main(String[] args) {
        // 线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.getConnection(1);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }

    }

}