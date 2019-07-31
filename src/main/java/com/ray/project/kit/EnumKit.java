package com.ray.project.kit;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.EnumUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class EnumKit extends EnumUtils {

    public static void main(String[] args) throws Exception {
        getEnumValue(InfoStateEnum.class,"getNum","getMsg");
    }
    public static void getEnumValue(Class enumClass,String ...methodNames) throws Exception {
        HashMap<Object,Object> result = Maps.newHashMap();
        Class<?> clz =enumClass;
        Object[] objects = clz.getEnumConstants();
        Method getCode = clz.getMethod(methodNames[0]);
        Method getName = clz.getMethod(methodNames[1]);
        for (Object obj : objects){
            result.put(getCode.invoke(obj),getName.invoke(obj));
        }
        System.out.println(JSON.toJSONString(result));
    }

}
