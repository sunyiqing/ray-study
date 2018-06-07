package com.ray.gof.observer.v2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yiqing on 2017/6/1.
 */
public class CurrentConditionsDisplay implements Observer,DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }

    public void display() {
        System.out.println(temperature+","+humidity);
    }

    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData  weatherData=(WeatherData)o;
            this.temperature=weatherData.getTemperature();
            this.humidity=weatherData.getHumidity();
            display();
        }
    }

}
