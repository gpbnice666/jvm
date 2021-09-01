package com.bo.jvm.nio;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存大小设置
 *      -Xmx20m -XX:MaxDirectMemorySize=20m
 *      -Xmx20m 设置堆最大内存
 *      -XX:MaxDirectMemorySize=20m 设置直接内存最大值
 * @author gpb
 * @date 2021/8/22 20:40
 */
public class MaxDirectMemorySizeTest {
    private static final  long _1MB = 1024 * 1024;


    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];

        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
