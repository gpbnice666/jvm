package com.bo.jvm.methodArea;

/**
 * 设置方法区或元空间的大小
 *  参数
 *      jdk7方法区
 *         -XX:PermSize=50m -XX:MaxPermSize=60m
 *          -XX:PermSize     设置永久代初始值分配内存，默认是 20.7M
 *          -XX:MaxPermSize 设置永久代最大可分配内存，32为机器默认是64M,64位机器82M
 *      jdk8元空间
 *            -XX:MetaspaceSize=50m -XX:MaxMetaspaceSize=60m
 *            -XX:MetaspaceSize   设置元空间的初始值大小 默认是 21m
 *            -XX:MaxMetaspaceSize  设置元空间最大内存 默认是-1 没有限制
 *       查看是否设置成功
 *              jps
 *              jinfo -flag 参数 进程ID
 * @author gpb
 * @date 2021/8/17 21:58
 */
public class MethodAreaDemo {

    public static void main(String[] args) {
        System.out.println("start...........");
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end......");
    }

}
