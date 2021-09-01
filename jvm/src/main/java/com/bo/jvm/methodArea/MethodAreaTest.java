package com.bo.jvm.methodArea;

/**
 * non-final的类变量
 * @author gpb
 * @date 2021/8/18 21:17
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
        /*
        输出
        * hello!
          1
        * */
    }

}
class Order{
    public static int count = 1;
    public static final int number = 2;

    public static void hello(){
        System.out.println("hello!");
    }
}