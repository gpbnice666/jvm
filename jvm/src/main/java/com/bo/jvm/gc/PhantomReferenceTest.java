package com.bo.jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用测试
 * @author gpb
 * @date 2021/8/29 16:44
 */
public class PhantomReferenceTest {

    public static PhantomReferenceTest obj;
    // 引用队列
    static ReferenceQueue<PhantomReferenceTest> phantomQueue = null;

    public static class CheckRefQueue extends Thread{
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceTest> objt = null;
                    try {
                       objt =  ( PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceTest实例被GC了");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用finalize方法");
        obj = this;
    }

    public static void main(String[] args) {
        CheckRefQueue checkRefQueue = new CheckRefQueue();
        // 设置守护线程
        checkRefQueue.setDaemon(true);
        checkRefQueue.start();

        phantomQueue = new ReferenceQueue<>();
        obj = new PhantomReferenceTest();
        PhantomReference<PhantomReferenceTest> phantomReference = new PhantomReference<PhantomReferenceTest>(obj,phantomQueue);

        try {
            System.out.println(phantomReference.get());
            // 将引用去除
            obj = null;
            // 第一次GC
            System.out.println("第一次 Gc");
            System.gc();
            Thread.sleep(1000);
            if(obj == null){
                System.out.println("obj is null");
            }else{
                System.out.println("obj is not null");
            }
            System.out.println("第二次 Gc");
            obj = null;
            System.gc();
            Thread.sleep(1000);
            if(obj == null){
                System.out.println("obj is null");
            }else{
                System.out.println("obj is not null");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
