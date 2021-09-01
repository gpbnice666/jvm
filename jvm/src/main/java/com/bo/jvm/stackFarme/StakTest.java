package com.bo.jvm.stackFarme;

/**
 * @author gpb
 * @date 2021/7/29 21:14
 * -Xss256k
 */
public class StakTest {
    private static int count = 0;
    public static void main(String[] args) {
        // at com.bo.jvm.stackFarme.StakTest.main(StakTest.java:11)
        System.out.println(count++);
        main(args);
    }
}
