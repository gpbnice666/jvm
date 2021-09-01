package com.bo.jvm.heap;

/**
 * 测试 大对象直接晋升到老年代
 * 参数
 *      -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/15 20:22
 */
public class YoungOldAreaTest {

    public static void main(String[] args) {
        byte[] buffer = new byte[1024 * 1024 * 20]; //20m
    }

}
