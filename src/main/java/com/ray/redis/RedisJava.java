package com.ray.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;

public class RedisJava {
    private static final int DEFAULT_MAX_ACTIVE = 1024;
    private static final int DEFAULT_MAX_IDLE = 200;
    private static final long DEFAULT_MAX_WAIT = 10000;
    private static final Integer DEFAULT_TIME_OUT = 10000;
    public static final String DEFAULT_IP_ADDR = "192.168.1.250";
    public static final int DEFAULT_IP_PORT = 6379;
    public static final String DEFAULT_PASSWORD = "123456";

    private static JedisPool pool;
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(DEFAULT_MAX_ACTIVE);
            config.setMaxIdle(DEFAULT_MAX_IDLE);
            config.setMaxWaitMillis(DEFAULT_MAX_WAIT);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            pool = new JedisPool(config, DEFAULT_IP_ADDR, DEFAULT_IP_PORT , DEFAULT_TIME_OUT, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        transactionDiscardTest();
        transactionExecTest();
        transactionTest();
    }


    /**
     * 事务回滚
     */
    public static void transactionDiscardTest(){
        Jedis jedis=pool.getResource();
        try{
            jedis.set("transaction_discard_test","123");
            Transaction multi = jedis.multi();
            multi.set("transaction_discard_test","456");
            multi.discard();
            System.out.println("事务提交后后:"+jedis.get("transaction_discard_test"));
        }finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }

    /**
     * 事务提交
     */
    public static void transactionExecTest(){
        Jedis jedis=pool.getResource();
        try{
            jedis.set("transaction_exec_test","123");
            Transaction multi = jedis.multi();
            multi.set("transaction_exec_test","456");
            multi.exec();
            System.out.println("事务提交后后:"+jedis.get("transaction_exec_test"));
        }finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }

    /**
     * 事务watch
     * 监控指定的keys，被监控的keys在事务之前发生修改，且事务中包含该key，如果事务执行exec，则exec将放弃所有事务中的命令。
     * watch -> multi ->set ->exec -> unwatch
     */
    public static void transactionTest(){
        Jedis jedis=pool.getResource();
        try{
            jedis.set("transaction_test","123");
            jedis.watch("transaction_test");
            jedis.set("transaction_test","999");
            Transaction multi = jedis.multi();
            multi.set("transaction_test","888");
            List<Object> exec = multi.exec();
            jedis.unwatch();
            System.out.println("事务提交后后:"+jedis.get("transaction_test"));
        }finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }


    /**
     * 简单数据结构
     */
    public static void baseStructure(){
        Jedis jedis = pool.getResource();
        //数据结构
        //key -value
        jedis.set("just_test_key_value","key-value");
        System.out.println(jedis.get("just_test_key_value"));
        jedis.del("just_test_key_value");
        //list
        jedis.lpush("news","aa");
        jedis.lpush("news","bb");
        jedis.lpush("news","cc");
        System.out.println(jedis.lpop("news"));
        jedis.rpush("news","dd","ee");
        jedis.rpop("news");

        //HASH
        jedis.hset("books","apple1","苹果1");
        jedis.hset("books","apple2","苹果2");
        jedis.hset("books","apple3","苹果3");
        System.out.println(jedis.hget("books","apple3"));

        //set
        jedis.sadd("bookes","1","2","3","4");

        //zset
        jedis.zadd("student",1,"xm");
        jedis.zadd("student",2,"xh");
        jedis.zadd("student",3,"xz");
        System.out.println(jedis.zrange("student",0,-1));

        //zset
        pool.close();


    }
}
