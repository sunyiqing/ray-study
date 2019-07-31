package com.ray.j2se.collection;

import java.util.ArrayList;
import java.util.List;

public class ArratListTest {
    public static void main(String[] args) {
        List<String> lists= new ArrayList<>(1);
        lists.add("1");
        lists.add("3");
        lists.add("2");
        System.out.println(lists.size());
    }
}
