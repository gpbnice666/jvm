package com.bo.jvm.heap;

/**
 * 测试栈式分配
 * 参数
 *      -Xmx1G -Xms1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *      	-XX:+DoEscapeAnalysis 显示开启逃逸分析
 *          -XX:+PrintEscapeAnalysis  查看逃逸分析的筛选结果
 * @author gpb
 * @date 2021/8/16 21:13
 */
public class StackAllocation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "ms");
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * 没有发生逃逸
     */
    private static void alloc() {
        User user = new User();
    }
    static class User{

    }
}
