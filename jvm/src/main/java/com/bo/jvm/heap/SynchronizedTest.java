package com.bo.jvm.heap;

/**
 * 同步省略 或 锁消除
 * 同步省略 如果一个对象被发现只能从一个线程被访问到，那么对于这对象的操作考虑同步的问题**
 * @author gpb
 * @date 2021/8/16 21:42
 */
public class SynchronizedTest {

    /**
     * 1.无法启动到 加锁的效果，因为 没进来一个线程 都会创建一个对象，锁不是一把锁
     * 2.如果该对象没有发生逃逸，可以不使用 synchronized
     * 3.当你Javap class文件的时候， 也会看到 monitorenter 和  monitorexit 指令，但是在编译器执行的时候会自动消除锁
     */
    public void method(){
       Object obj = new Object();
       synchronized (obj){
           System.out.println(obj);
       }
   }

}
