package com.ray.j2se.thread.test.basic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

// main方法输出的线程内容
public class MultiThread {
    /**
     * 5:Monitor Ctrl-Break
     * 4:Signal Dispatcher 分发处理发给JVM的信号线程
     * 3:Finalizer
     * 2:Reference Handler
     * 1:main
     * @param args
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
        }
    }
}
