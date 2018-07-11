package com.ray.apache.common;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by yiqing on 2018/7/11.
 */
public class ApacheCommonsTest {

    public static void main(String[] args) {
        arrayUtils();
    }


    public static void arrayUtils(){
        String[] ss = new String[0];
        ArrayUtils.add(ss,"123");
        ArrayUtils.add(ss,"1");
        System.out.println(ss.toString());
    }


}
