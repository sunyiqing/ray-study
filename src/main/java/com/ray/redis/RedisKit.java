package com.ray.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisKit {
    private static final int DEFAULT_MAX_ACTIVE = 1024;
    private static final int DEFAULT_MAX_IDLE = 200;
    private static final long DEFAULT_MAX_WAIT = 10000;
    private static final Integer DEFAULT_TIME_OUT = 10000;
    public static final String DEFAULT_IP_ADDR = "192.168.200.240";
    public static final int DEFAULT_IP_PORT = 6379;
    public static final String DEFAULT_PASSWORD = "";

    public final static RedisKit redisKit = new RedisKit();
    private static JedisPool pool;
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(DEFAULT_MAX_ACTIVE);
            config.setMaxIdle(DEFAULT_MAX_IDLE);
            config.setMaxWaitMillis(DEFAULT_MAX_WAIT);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            pool = new JedisPool(config, DEFAULT_IP_ADDR, DEFAULT_IP_PORT , DEFAULT_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        RedisKit redisKit = RedisKit.redisKit;
//        redisKit.set("haha_syq","123");
//        System.out.println(redisKit.get("haha_syq"));

        Integer i1 =1;
        Integer i2 =1;
        System.out.println(i1.equals(i2));
        if(!i1.equals(i2)){
            System.out.println(111);
        }
    }

    public void set(String key, String value){
        Jedis jedis=pool.getResource();
        jedis.set(key,value);
    }
    public String get(String key){
        Jedis jedis=pool.getResource();
        return jedis.get(key);
    }
    public void del(String key){
        Jedis jedis=pool.getResource();
        jedis.del(key);
    }
    public void setEx(String key, String value, int timeOut, TimeUnit timeUnit){

        Jedis jedis=pool.getResource();
        jedis.setex(key,timeOut,value);
        jedis.close();;
    }

    public Map<String,String> hGetAll(String key){
        Jedis jedis=pool.getResource();
        return jedis.hgetAll(key);
    }
    public String hGet(String key,String field){
        Jedis jedis=pool.getResource();
        return jedis.hget(key,field);
    }

    public void hSet(String key,String field,String value){
        Jedis jedis=pool.getResource();
        jedis.hset(key,field,value);
    }
}
