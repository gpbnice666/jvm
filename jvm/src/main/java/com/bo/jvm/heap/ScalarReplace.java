package com.bo.jvm.heap;

/**
 * 标量替换测试
 * 参数
 *      -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
 *       -XX:+DoEscapeAnalysis 显示开启逃逸分析
 *       -XX:+EliminateAllocations 开启标量替换(默认打开)，允许将对象打散分配在栈上
 * @author gpb
 * @date 2021/8/16 22:09
 */
public class ScalarReplace {
    public static class User{
        public int id;
        public String name;
    }

    /**
     * 未发生逃逸
     */
    public static void alloc(){
        User user = new User();
        user.id = 5;
        user.name = "标量替换";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "ms");
    }
}

