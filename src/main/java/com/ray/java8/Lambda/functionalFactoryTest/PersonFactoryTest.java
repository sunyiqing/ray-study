package com.ray.java8.Lambda.functionalFactoryTest;

import com.alibaba.fastjson.JSON;

public class PersonFactoryTest {


    public static void main(String[] args) {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("sun","yq");
        System.out.println(JSON.toJSONString(person));
    }
}
