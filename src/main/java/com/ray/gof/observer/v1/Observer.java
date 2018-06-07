package com.ray.gof.observer.v1;

/**
 * 观察者
 * Created by yiqing on 2017/6/1.
 */
public interface Observer {
    public void update(float temp,float humidity,float pressure);
}
