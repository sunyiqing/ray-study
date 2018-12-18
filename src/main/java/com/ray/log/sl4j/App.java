package com.ray.log.sl4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
　　* Hello world!
　　*/
public class App {

private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("logback 成功了");
        logger.error("logback 成功了");
        logger.warn("logback 成功了");
        logger.debug("logback 成功了");
    }
}
