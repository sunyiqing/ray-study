package com.ray.java8.Lambda.functionalInterfaceTest;

public class ConverterTest {

    public static void main(String[] args) {
        // 1.使用 lambda表达式，实现方法
        Converter<String,Integer> converter = from -> Integer.valueOf(from);
        Integer convered = converter.convert("123");
        System.out.println(convered);

        //2.包装Something 方法，实现
        Something something = new Something();
        Converter<String,String> converter1 = something::startWith;
        String convered1 = converter1.convert("syq");
        System.out.println(convered1);

    }
}
