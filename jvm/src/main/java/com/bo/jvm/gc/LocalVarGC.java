package com.bo.jvm.gc;

/**
 * 参数
 * -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/28 19:47
 */
public class LocalVarGC {

    /**
     * 不会被回收
     */
    public void localVarGC1(){
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }

    /*
     * 会被回收
     */
    public void localVarGC2(){
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    /**
     * 不会被回收
     * 局部变量 2
     * this 0
     * buffer 1
     */
    public void localVarGC3(){
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * buffer 会被回收
     * 局部变量表
     * this 0
     * buffer 1
     * value  1 占用了槽1的位置
     */
    public void localVarGC4(){
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    /**
     * 会被回收
     */
    public void localVarGC5(){
        localVarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
        localVarGC.localVarGC1();
    }
}
