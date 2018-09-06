package com.ray.gof.decotator;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("draw a circle");
    }
}
