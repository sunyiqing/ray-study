package com.ray.apache.common;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yiqing on 2018/7/11.
        */
public class ApacheCommonsTest {

    public static void main(String[] args) {

    }



    public static void arrayUtils(){
        String[] ss = new String[0];
        ArrayUtils.add(ss,"123");
        ArrayUtils.add(ss,"1");
        System.out.println(ss.toString());
    }

    public static void stringUtils(){

    }

    public static void  validate(){
        Validate.isTrue(1==1);
        Validate.notNull(1);
        String[] test = new String[0];
        Validate.noNullElements(test);
        List<String> strs= Lists.newArrayList();
        strs.add("1");strs.add("2");
        Validate.validIndex(strs,1);
        String name="SYQ";
        //不可以有空格
        Validate.notBlank(name);
        Validate.notEmpty(name);
        Validate.validState(true);
        BigDecimal b1=BigDecimal.ZERO;
        BigDecimal b2=BigDecimal.TEN;
        Validate.exclusiveBetween(b1,b2,BigDecimal.ONE);

        //校验是否是一个实例
        Validate.isInstanceOf(BigDecimal.class,BigDecimal.ONE);
        //校验是否继承
        Validate.isAssignableFrom(Comparable.class,BigDecimal.class);
    }


    public static void  stringEscapeUtils(){
    }


}
