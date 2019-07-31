package com.ray.java8.Lambda;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DeepStreamsLambdaTest {

    public static void main(String[] args) {
        DeepStreamsLambdaTest test = new DeepStreamsLambdaTest();
        test.test();

    }

    public void test(){
        //collect
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        List<Person> filterPersion = persons.stream().filter(p -> p.name.startsWith("M")).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(filterPersion));
        Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);
        IntSummaryStatistics intSummaryStatistics =persons.stream().collect(Collectors.summarizingInt(p -> p.age));
        System.out.println(JSON.toJSONString(intSummaryStatistics));
        String phrase = persons.stream().filter(a -> a.age >18).map(a -> a.name).collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        System.out.println(phrase);

        //自定义收集器
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner("|"),
                        (j, p) -> j.add(p.name.toUpperCase()),
                        (j1, j2) -> j1.merge(j2),
                        StringJoiner::toString);
        String names = persons.stream().collect(personNameCollector);
        System.out.println(names);
    }


    class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
