package com.bo.jvm.stackFarme;

/**
 * @author gpb
 * @date 2021/8/9 22:12
 */
public class StringBuilderTest {


    public static void method1(){
        // StringBuilder 线程不安全  stringBuilder变量在方法内局部变量时线程安全的
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        stringBuilder.append("b");
    }

    public static void method2(StringBuilder stringBuilder){
        // stringBuilder时线程不安全的
        stringBuilder.append("a");
        stringBuilder.append("b");
    }
    /**
     *  stringBuilder 不安全 如果,返回的时String类型,
     *  那么stringBuilder是安全的,返回的String 那个字符时不安全的
     * @return
     */
    public static StringBuilder method3(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        stringBuilder.append("b");
        return stringBuilder;
    }
}
