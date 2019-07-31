package com.ray.java8.Lambda.functionalInterfaceTest;

@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);


}
