package com.ray.lambda;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 *
 * 基本语法:
 *(parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 */
public class LambdaTest {

    public static void main(String[] args) {

        lambdaTest();
        listStreams();
    }


    /////////////////////参考博客：https://blog.csdn.net/renfufei/article/details/24600507////////////////////////////////
    /**
     * 基本语法
     */
    private static void lambdaTest(){
        String[] atp={"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> atps=Arrays.asList(atp);
        //循环
        for (String s:atps) {
            System.out.println(s);
        }
        atps.forEach(s-> System.out.println(s));
        atps.forEach(System.out::println);

        //匿名类
        new Thread(()->{
            System.out.println("hello world");
        }).start();
    }


    /**
     * list lambda
     */
    private static void listStreams(){
        //lambdas streams
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
        //循环输出
        javaProgrammers.forEach(j-> System.out.println(j.getAge()));
        //输出年纪超过30的人
        javaProgrammers.stream().filter(p->(p.getAge()>30)).forEach(p-> System.out.println(p.getAge()+p.getFirstName()));

        //自定义filter
        Predicate<Person> personPredicate=p->p.getAge()>20;
        javaProgrammers.stream().filter(personPredicate).forEach(p-> System.out.println(p.getFirstName()));

        //limit
        System.out.println("------limit-------");
        javaProgrammers.stream().limit(3).forEach(p-> System.out.println(p.getFirstName()));

        //排序
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortedJavaProgrammers =javaProgrammers
                .stream()
                .sorted((p,p2)->p.getFirstName()
                        .compareTo(p.getFirstName()))
                .limit(5)
                .collect(toList());
        sortedJavaProgrammers .forEach(person -> System.out.println( person.getFirstName()));

        System.out.println("根据 salary 排序 Java programmers:");
        sortedJavaProgrammers=javaProgrammers.stream().sorted((p,p1)->(p.getSalary() - p1.getSalary())).collect(toList());
        sortedJavaProgrammers.forEach(p-> System.out.println(p.getFirstName()+p.getSalary()));

        System.out.println("工资最低的 Java programmer:");
        Person p=javaProgrammers.stream().min((p1,p2)->(p1.getSalary() - p2.getSalary())).get();
        System.out.println(p.getFirstName()+p.getSalary());

        System.out.println("工资最高的 Java programmer:");
        Person pe=javaProgrammers.stream().max((p1,p2)->(p1.getSalary()-p2.getSalary())).get();
        System.out.println(pe.getFirstName()+pe.getSalary());

        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers =phpProgrammers.stream().map(Person::getFirstName).collect(joining(";"));
        System.out.println(phpDevelopers);

        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = phpProgrammers.stream().map(Person::getFirstName).collect(toSet());
        System.out.println(javaDevFirstName);

        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));
        javaDevFirstName.forEach(System.out::println);

        System.out.println("工资和:");
        double d=javaProgrammers.stream().mapToDouble(p11->p11.getSalary()).sum();
        System.out.println(d);

        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());

    }
    static class Person {

        private String firstName, lastName, job, gender;
        private int salary, age;

        public Person(String firstName, String lastName, String job,
                      String gender, int age, int salary)       {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }



}
