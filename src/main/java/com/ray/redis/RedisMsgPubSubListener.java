package com.ray.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * 发布订阅实现类
 */
public class RedisMsgPubSubListener extends JedisPubSub {
    public RedisMsgPubSubListener() {
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel:[%s], message: [%s]", channel, message));
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel:[%s], message: [%s]",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel:[%s], message: [%s]",
                channel, subscribedChannels));

    }
}
