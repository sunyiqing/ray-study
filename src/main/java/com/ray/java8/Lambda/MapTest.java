package com.ray.java8.Lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ray.java8.Lambda.functionalFactoryTest.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapTest {

    public static void main(String[] args) {

        //computeIfAbsent 不需要判断的map是否为空
        Map<String, List<String>> map = Maps.newHashMap();
        List<String> strings = map.computeIfAbsent("syq", k -> new ArrayList<>());
        strings.add("haha");
        System.out.println(JSON.toJSONString(map));




        // list 转 map
        Person p1 = new Person("s1","s2");
        Person p2 = new Person("a2","a2");
        Person p3 = new Person("b1","b2");
        Person p4 = new Person("c1","c2");
        List<Person> personList = Lists.newArrayList(p1,p2,p3,p4);
        Map<String, Person> pmap = personList.stream().collect(Collectors.toMap(Person::getFirstName, p -> p));
        System.out.println(JSON.toJSONString(pmap));


    }
}
