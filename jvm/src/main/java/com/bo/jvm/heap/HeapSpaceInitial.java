package com.bo.jvm.heap;

/**
 * -Xms 用来设置堆空间(年轻代+老年代)的初始值大小
 *      -X 时JVM的运行参数
 *      mx 时memory start
 * -Xmx 用来设置堆空间(年轻代+老年代)最大内存
 *
 * 当你自己设置堆的内存大小的时候，你会发现，你设置堆的最大内存，通过Java代码查看堆的内存会少一些
 *  是因为，系统没有计算，S0 或者 S1 其中的一个区域
 *
 *  查看设置的参数的方式：
 *                  1. jps  jstat -gc 进程Id
 *                  2. -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/14 19:24
 */
public class HeapSpaceInitial {

    public static void main(String[] args) {
        // 返回Java虚拟机中的堆内存总量
        long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        // 返回Java虚拟机试图使用最大内存的大小
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms:初始值大小：" + initalMemory + "M");
        System.out.println("-Xmx:堆最大小：" + maxMemory + "M");

        System.out.println("系统内存大小为：" + initalMemory * 64.0 / 1024 + "G");
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

       /* try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
