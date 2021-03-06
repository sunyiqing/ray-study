package com.ray.apache.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yiqing on 2018/7/11.
        */
public class ApacheCommonsTest {

    public static void main(String[] args) {
//        enumUtils();

//        NumberUtilsTest();
//        String mesg = String.format(BuildCacheErrorEnum.TASK_TYPE_VERIFY_FAILURE.getMsg(),"1","2","3","4","5","6");
//        System.out.printf(mesg);

//        TaskTypeEnum taskTypeEnum = TaskTypeEnum.DELETE;
//        switch (taskTypeEnum) {
//            case NEW:
//
//                break;
//            case DELETE:
//                System.out.println(1);
//                break;
//            case UPDATE:
//                break;
//        }


//        long beginTime = new Date().getTime();
//        NumberFormat df = NumberFormat.getInstance();
//        df.setGroupingUsed(false);
//        Double str = Double.valueOf("1000"+ df.format(beginTime));
//        double score = Double.valueOf(Double.valueOf("1000" + df.format(beginTime)));
//        System.out.println(score);

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


    public static void NumberUtilsTest(){
        boolean flag = NumberUtils.isNumber(null);
        System.out.printf(flag+"");
    }

    public static void  enumUtils(){
        List<User> users=EnumUtils.getEnumList(User.class);
        users.stream().forEach(User::getName);

        User u=EnumUtils.getEnum(User.class,"syq");
        System.out.println(u.getName());

        Map<String,User> maps=EnumUtils.getEnumMap(User.class);
        User map=maps.get("haha");
        System.out.println(map.getAge());



    }


    public static void dateUtilsTest(){
        //date format utils
//        DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATE_FORMAT);

    }

}
enum User{
    syq("syq",26,"n"),
    haha("haha",26,"n");

    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
