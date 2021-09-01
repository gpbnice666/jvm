package com.bo.jvm.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存测试 nio效率
 * @author gpb
 * @date 2021/8/22 19:57
 */
public class NioBuffer {

    private static final String TO = "文件地址";
    private static final int _100Mb = 1024 * 1024 * 100;

    public static void main(String[] args) {
        long sum = 0;
        String src = "文件地址";
        for (int i = 0; i < 3; i++) {
            String dest = "文件地址" + i +"文件格式";
            sum += io(src,dest);
            sum += directBuffer(src,dest);
        }
    }

    private static long directBuffer(String src, String dest) {
        long start = System.currentTimeMillis();

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100Mb);
            while (inChannel.read(byteBuffer) != -1) {
                // 修改为读数据模式
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                // 清空
                byteBuffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inChannel !=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return start - System.currentTimeMillis();
    }

    private static long io(String src, String dest) {
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            byte[] buffer = new byte[_100Mb];
            while (true) {
                int len = fis.read(buffer);
                if (len == -1){
                    break;
                }
                fos.write(buffer,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return start - System.currentTimeMillis();
    }

}
