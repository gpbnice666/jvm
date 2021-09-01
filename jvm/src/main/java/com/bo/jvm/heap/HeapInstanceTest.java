package com.bo.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * 参数：
 *      -Xms600m -Xmx600m -XX:+PrintGCDetails
 *
 * @author gpb
 * @date 2021/8/15 16:31
 */
public class HeapInstanceTest {
    private byte[] buffer = new byte[new Random().nextInt(1025 * 200)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> heapInstanceTests = new ArrayList<>();
        String aa = "aa";
        aa.intern();
        while (true) {
            heapInstanceTests.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
