package com.ray.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPushSub {
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

    /**
     * publish
     */
    @Test
    public void publish(){
        Jedis jedis=pool.getResource();
        try{
            jedis.publish("first_channel","hello world");
        }finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }

    /**
     * subscribe
     */
    @Test
    public void subscribe(){
        Jedis jedis=pool.getResource();
        try{
            RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
            jedis.subscribe(listener, "first_channel");
        }finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }

}
