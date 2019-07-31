package com.ray.java8.Lambda.functionalFactoryTest;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
//    P create();
//    P create(String firstName);
}
