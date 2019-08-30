package com.ray.project.kit;

import java.util.ArrayList;
import java.util.List;

public class ListSubUtil {

    //把List的元素按size的大小，分组存放
    public static <T> List<List<T>> listSubArray(List<T> list, int size) {
        List<List<T>> listSubArray = new ArrayList<List<T>>();
        List<T> subList = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % size == 0) {
                subList = new ArrayList<T>();
                listSubArray.add(subList);
            }
            subList.add(list.get(i));
        }
        return listSubArray;
    }
}
