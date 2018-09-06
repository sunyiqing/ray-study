package com.ray.gof.decotator;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        setRedBorder(shape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
