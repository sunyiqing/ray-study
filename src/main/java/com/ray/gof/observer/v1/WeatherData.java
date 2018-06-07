package com.ray.gof.observer.v1;

import java.util.ArrayList;

/**
 * Created by yiqing on 2017/6/1.
 */
public class WeatherData implements Subject {
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers=new ArrayList();
    }
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        int i=observers.indexOf(observer);
        if(i>0)
            observers.remove(i);
    }

    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer o= (Observer) observers.get(i);
            o.update(temperature,humidity,pressure);
        }
    }

    public void measurementsCharged(){
        notifyObserver();
    }
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsCharged();
    }
}
