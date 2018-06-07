package com.ray.gof.observer.v1;

/**
 * Created by yiqing on 2017/6/1.
 */
public class CurrentConditionsDisplay implements Observer,DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherDate;
    public CurrentConditionsDisplay(Subject weatherDate){
        this.weatherDate=weatherDate;
        weatherDate.registerObserver(this);
    }
    public void display() {
        System.out.println(temperature+","+humidity);
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature=temp;
        this.humidity=humidity;
        display();
    }
}
