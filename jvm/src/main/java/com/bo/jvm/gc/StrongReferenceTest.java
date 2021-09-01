package com.bo.jvm.gc;

/**
 * 强引用测试
 * @author gpb
 * @date 2021/8/28 23:29
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer("强引用");

        StringBuffer str = buffer;

        buffer = null;
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

}
