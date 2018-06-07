package com.ray.gof.observer.v2;

import java.util.Observable;

/**
 * Created by yiqing on 2017/6/1.
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){}

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
    public float getTemperature(){return temperature;}
    public float getHumidity(){return humidity;}
    public float getPressure(){return pressure;}


}
