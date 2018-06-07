package com.ray.gof.observer.v1;

/**
 * Created by yiqing on 2017/6/1.
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData=new WeatherData();
        CurrentConditionsDisplay display=new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80,90,100);

    }
}
