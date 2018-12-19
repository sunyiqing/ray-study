package com.ray.project.kit;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumberKit extends NumberUtils {

    public static boolean isLong(String value) {
        long defaultValue = -9999L;
        if(StringUtils.equals(value,String.valueOf(defaultValue))) return true;
        long result = toLong(value,defaultValue);
        return defaultValue == result ? false : true;
    }

    public static void main(String[] args) {
        List<Integer> list = null;
//        list.add(1);
//        list.add(2);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
