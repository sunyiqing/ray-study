package com.ray.log.log4j;


import org.apache.log4j.Logger;
import org.junit.Test;
 
 
/**
 * @author xiaojing.xie
 * @create 2017-09-18
 **/
public class TestLog4j {
    @Test
    public void test3(){
        Logger log = Logger.getLogger("testLogger");
        log.info("info");
        log.debug("debug");
        log.error("error");
        log.warn("warn");
        log.trace("trace");
    }
    @Test
    public void test2(){
        Logger log = Logger.getLogger("testCategoryLogger");
        log.info("info");
        log.debug("debug");
        log.error("error");
        log.warn("warn");
        log.trace("trace");
    }
    @Test
    public void test1(){
        Logger log = Logger.getLogger("testAppender");
        log.info("info");
        log.debug("debug");
        log.error("error");
        log.warn("warn");
        log.trace("trace");
    }
}
