package com.bo.jvm.nio;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
*
 *  直接内存OOM测试
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
*  @author gpb
* @date 2021/8/22 20:30
*/
public class BufferOOM {
    private static final int BUFFER = 1024 * 1024 * 20; // 20MB

    public static void main(String[] args) {
        ArrayList<ByteBuffer> byteBuffers = new ArrayList<>();
        int count = 0;
        try{
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
                byteBuffers.add(byteBuffer);
                count ++;
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(count);
        }
    }

}
