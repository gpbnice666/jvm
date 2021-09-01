package com.bo.jvm.gc;

/**
 * @author gpb
 * @date 2021/8/28 19:41
 */
public class SystemGC {

    public static void main(String[] args) {
        new SystemGC();
        // 提醒jvm的垃圾回收器执行GC
        System.gc();

        // Runtime.getRuntime().gc();

        // 轻质调用使用引用的对象的finalize()方法
        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGC 重写了finalize() 执行GC了");
    }
}
