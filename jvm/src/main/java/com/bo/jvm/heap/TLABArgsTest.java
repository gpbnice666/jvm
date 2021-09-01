package com.bo.jvm.heap;

/**
 * 测试 -XX:UseTLAB 参数是否开启的情况
 *
 *  查看步骤：
 *          jsp 查看该线程ID
 *          jinfo -flag UseTLAB
 * @author gpb
 * @date 2021/8/15 20:48
 */
public class TLABArgsTest {

    public static void main(String[] args) {
        System.out.println("嗨喽，TLAB");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
