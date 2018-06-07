package com.ray.gof.observer.v1;

/**
 *  主题
 * Created by yiqing on 2017/6/1.
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver();


}
