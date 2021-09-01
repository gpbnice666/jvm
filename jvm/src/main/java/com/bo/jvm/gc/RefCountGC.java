package com.bo.jvm.gc;

/**
 * -XX:+PrintGCDetails
 * java没有使用引用计数法
 * @author gpb
 * @date 2021/8/26 21:07
 */
public class RefCountGC {

    /**
     * 成员属性唯一的作用就是一个占位符 5m
     */
    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        System.gc();
    }

}
