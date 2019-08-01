package com.ray.java8.Lambda;

import com.alibaba.fastjson.JSON;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DeepStreamsLambdaTest {

    public static void main(String[] args) {
        DeepStreamsLambdaTest test = new DeepStreamsLambdaTest();
//        test.test();
//        test.flatMapTest();
//        test.reduceTest();
//        Optional.of(null);
        Optional.ofNullable(null);

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

        List<String> lists = persons.stream().map(p -> p.name).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(lists));


        persons.stream().forEach(System.out::println);
        List<Person> personList = persons.stream().collect(Collectors.toList());
        System.out.println(JSON.toJSONString(personList));
        persons.stream().mapToDouble(p -> p.age).min();
        persons.stream().mapToInt(p -> p.age).sum();
        persons.stream().mapToInt(p -> p.age).count();
        IntSummaryStatistics intSummaryStatistics1 = persons.stream().mapToInt(p -> p.age).summaryStatistics();
        persons.stream().mapToInt(p -> p.age).sorted().forEach(System.out::println);
        persons.stream().map(p -> p.name).distinct();

        //reduce
        System.out.println(persons.stream().map(p ->p.age).reduce(Math::max).get());
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (min, item) -> Math.min(min, item)));
        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);
        //flat map
        List<Person> persons1 =
                Arrays.asList(
                        new Person("Max1", 181),
                        new Person("Peter1", 231),
                        new Person("Pamela1", 231),
                        new Person("David1", 121));
        //PEAK
        //数据流的不同类型
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        //joining
        persons.stream().map(person -> person.name).collect(Collectors.joining(";"));
//        personList.stream().filter(person -> person.age > 10).collect(Collectors.groupingBy(String::length));


    }

    public void flatMapTest(){
        List<Foo> foos = new ArrayList<>();

        // create foos
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        // create bars
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

    }

    public void reduceTest(){

        //collect
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        //取最大年龄
        persons.stream().reduce((p1 , p2) -> p1.age > p2.age ? p1 : p2);

        //拼接出新对象
        Person person = persons.stream().reduce(new Person("",0),(p1,p2) -> {
            p1.age += p2.age;
            p1.name += p2.name;
            return p1;
        });
        System.out.format("name=%s; age=%s", person.name, person.age);

        //累加所有年级
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);  // 76
    }

    public void optionalTest(){
       Optional.ofNullable(null);
       Optional.of(null);

    }
    class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
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
