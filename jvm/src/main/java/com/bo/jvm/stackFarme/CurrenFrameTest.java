package com.bo.jvm.stackFarme;

/**
 * @author gpb
 * @date 2021/7/29 20:47
 */
public class CurrenFrameTest {

    public static void main(String[] args) {
        CurrenFrameTest currenFrameTest = new CurrenFrameTest();
        currenFrameTest.method1();
    }

    public void method1(){
        System.out.println("method1开始执行");
        method2();
        System.out.println("method1执行结束");
    }

    private int method2() {
        System.out.println("method2开始执行");
        int i = 10;
        int v = (int)method3();
        System.out.println("method2即将结束");
        return i + v;
    }

    private double method3() {
        System.out.println("method3开始执行");
        double j = 20;
        System.out.println("method3即将结束");
        return j;
    }


}
