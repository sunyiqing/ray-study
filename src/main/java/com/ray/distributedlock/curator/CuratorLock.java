package com.ray.distributedlock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

/**
 * https://www.cnblogs.com/lvjingang/p/8316599.html
 * 基于 curator zookeeper实现
 */
public class CuratorLock {
    //初始化url
    private static final String url="127.0.0.1:2181";

    private static int count=10;

    public static void main(String[] args) throws Exception {
        for(int i=0;i<10;i++){
            new Thread(() -> {
                CuratorFramework zk= CuratorFrameworkFactory.builder()
                        .sessionTimeoutMs(5000)
                        .retryPolicy(new RetryNTimes(3,1000))
                        .connectionTimeoutMs(50000)
                        .connectString(url)
                        .build();
                zk.start();
                //分布式锁
                InterProcessMutex lock=new InterProcessMutex(zk,"/lock");
                try {
                    //枷锁
                    lock.acquire();
                    get();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        //释放锁
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static void get(){
        count--;
        System.out.println(count);
    }
}
