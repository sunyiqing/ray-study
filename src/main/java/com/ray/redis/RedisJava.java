package com.ray.redis;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

public class RedisJava {

    private static ShardedJedisPool pool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.1.225", 6379);
        jedisShardInfo1.setPassword("123456");
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) {
//        baseStructure();

        ShardedJedis jedis = pool.getResource();


    }




    /**
     * 简单数据结构
     */
    public static void baseStructure(){
        ShardedJedis jedis = pool.getResource();
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
