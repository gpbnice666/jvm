package com.bo.jvm.methodArea;

import java.io.Serializable;

/**
 * 方法区结构测试
 * @author gpb
 * @date 2021/8/18 21:00
 */
public class MethodInnerStrucTest extends Object implements Comparable<String>, Serializable {

    public int num = 20;
    private static String str = "方法的内部结构";

    protected void test1(){
        int count = 10;
        System.out.println("count = " + count);
    }

    public static int test2(int cal){
        int result = 0;
        try{
            int value = 30;
            result = value / result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return result;
    }


    @Override
    public int compareTo(String o) {
        return 0;
    }
}
