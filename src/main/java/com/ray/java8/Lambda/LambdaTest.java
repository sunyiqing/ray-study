package com.ray.java8.Lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ray.java8.Lambda.functionalFactoryTest.Person;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String[] args) {
        sortTest();
        System.out.println("-----------------------------");
        innerFuncitonTest();
        System.out.println("-----------------------------");
        streamsTest();
        System.out.println("-----------------------------");
        parallelStreamsTest();
        System.out.println("-----------------------------");
        deepStreamsTest();
    }

    private static void sortTest(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(JSON.toJSONString(names));

        List<String> names1 = Arrays.asList("peter", "anna", "mike", "xenia");
        names1.sort((a, b) -> b.compareTo(a));
        System.out.println(JSON.toJSONString(names1));

        List<String> names2 = Arrays.asList("peter", "anna", "mike", "xenia");
        names2.sort(String::compareTo);
        System.out.println(JSON.toJSONString(names1));
    }

    private static void innerFuncitonTest(){
        //predicates
        Predicate<String> predicateStr = (s) -> s.length() > 0;
        Predicate<String> otherPredicateStr = StringUtils::isEmpty;
        System.out.println(predicateStr.test("123"));
        System.out.println(predicateStr.negate().test("syq"));
        System.out.println(predicateStr.and(otherPredicateStr).test("123"));
        System.out.println(predicateStr.or(otherPredicateStr).test("123"));

        Predicate<Boolean> nonNull = Objects::nonNull;
        System.out.println(nonNull.test(null));
        System.out.println(nonNull.test(true));

        Predicate<String> isEmpty = StringUtils::isEmpty;
        Predicate<String> isNotEmpty = StringUtils::isNotEmpty;
        System.out.println(isEmpty.test(""));
        System.out.println(isNotEmpty.test("123"));

        //function
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));

        //Suppliers
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person

        //Consumers
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.andThen(p -> System.out.println("nihao ,"+p.getFirstName())).accept(new Person("sun","yq"));
        greeter.accept(new Person("Luke", "Skywalker"));

        //Comparators
        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        // > 0
        comparator.compare(p1, p2);
        // < 0
        comparator.reversed().compare(p1, p2);

        //Optionals
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    private static void streamsTest(){
        List<String> list = new ArrayList<>();
        list.add("ddd2");
        list.add("aaa2");
        list.add("bbb1");
        list.add("aaa1");
        list.add("bbb3");
        list.add("ccc");
        list.add("bbb2");
        list.add("ddd1");
        //filter
        list.stream().filter(StringUtils::isNotEmpty).forEach(System.out::println);
        // sorted
        list.stream().sorted((a,b) -> a.compareTo(b)).forEach(s -> System.out.println(s));
        // map
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        //math
        boolean anyStartsWithA =
                list
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                list
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                list
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true

        //Count
        System.out.println(list.stream().filter(s -> s.startsWith("a")).count());

        //reduce
        list.stream().sorted(String::compareTo).reduce((s1,s2) -> s1 +"#"+ s2).ifPresent(System.out::println);
    }

    private static void parallelStreamsTest(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t01 = System.nanoTime();
        long count1 = values.stream().sorted().count();
        System.out.println(count1);
        long t11 = System.nanoTime();
        long millis1 = TimeUnit.NANOSECONDS.toMillis(t11 - t01);
        System.out.println(String.format("sequential sort took: %d ms", millis1));


        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

    }

    private static void deepStreamsTest(){
        //不加foreach不会输出
        //加foreach后才会输出
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //
        Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
            System.out.println("map："+s);
            return s.toUpperCase();
        }).anyMatch(s -> {
            System.out.println("map："+s);
            return s.startsWith("A");
        });
    }

}
