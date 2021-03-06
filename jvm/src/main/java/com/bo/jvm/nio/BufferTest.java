package com.bo.jvm.nio;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * IO               NIO(new io/non-Blocking IO)非堵塞
 * byte[]/char[]    Buffer
 * Stream           Channel
 * 查看直接内存的占用与释放
 * @author gpb
 * @date 2021/8/22 16:59
 */
public class BufferTest {

    private static final int BUFFER = 1024 * 1024 * 1024; // 1g

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER);
        System.out.println("直接内存分配完毕，请求指示");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放！");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }

}
