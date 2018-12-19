package com.ray.project.dj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
            config.setMaxIdle(DEFAULT_MAX_IDLE);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            pool = new JedisPool(config, DEFAULT_IP_ADDR, DEFAULT_IP_PORT , DEFAULT_TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RedisKit redisKit = RedisKit.redisKit;
        redisKit.set("haha_syq","123");
        System.out.println(redisKit.get("haha_syq"));
    }

    public void set(String key, String value){
        Jedis jedis=pool.getResource();
        jedis.set(key,value);
    }
    public String get(String key){
        Jedis jedis=pool.getResource();
        return jedis.get(key);
    }

    public Long del(String key){
        Jedis jedis=pool.getResource();
        return jedis.del(key);
    }
    public void setEx(String key, String value, int timeOut, TimeUnit timeUnit){

        Jedis jedis=pool.getResource();
        jedis.setex(key,timeOut,value);
    }

    public void setEx(String key, String value, Long timeOut, TimeUnit timeUnit){

        Jedis jedis=pool.getResource();
        jedis.setex(key, Integer.valueOf(String.valueOf(timeOut)),value);
    }

    public Map<String,String> hGetAll(String key){
        Jedis jedis=pool.getResource();
        return jedis.hgetAll(key);
    }
    public String hGet(String key, String field){
        Jedis jedis=pool.getResource();
        return jedis.hget(key,field);
    }

    public void hSet(String key, String field, String value){
        Jedis jedis=pool.getResource();
        jedis.hset(key,field,value);
    }
    public void hMSet(String key, Map<String,String> value){
        Jedis jedis=pool.getResource();
        jedis.hmset(key,value);
    }

    public long ttl(String key){
        Jedis jedis=pool.getResource();
        return jedis.ttl(key);
    }

    public void expire(String key, long timeOut, TimeUnit unit){
        Jedis jedis=pool.getResource();
        jedis.expire(key, (int) timeOut);
    }

    public void zAdd(String key, double sort, String value){
        Jedis jedis=pool.getResource();
        jedis.zadd(key, sort,value);
    }

    public void sAdd(String key, String...value){
        Jedis jedis=pool.getResource();
        jedis.sadd(key ,value);
    }

    public void hDel(String key, String...value){
        Jedis jedis=pool.getResource();
        jedis.hdel(key ,value);
    }

    public void zRem(String key, String...value){
        Jedis jedis=pool.getResource();
        jedis.zrem(key ,value);
    }
    public Long hLen(String key){
        Jedis jedis=pool.getResource();
        return jedis.hlen(key);
    }

    public void sRem(String key, String...value){
        Jedis jedis=pool.getResource();
        jedis.srem(key ,value);
    }
}
