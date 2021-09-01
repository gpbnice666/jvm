package com.bo.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * stw 测试
 * @author gpb
 * @date 2021/8/28 20:29
 */
public class StopTheWorldDemo {

    public static class WorkThread extends Thread{
        List<byte[]> list = new ArrayList<byte[]>();


        @Override
        public void run() {
            while (true){
                for (int i = 0; i < 1000; i++ ){
                    byte[] buffer = new byte[1024];
                    list.add(buffer);
                }
                if (list.size() > 10000) {
                    list.clear();
                    System.gc();
                }
            }
        }
    }



    public static class PrintThread extends Thread{
        public final long START_TIME = System.currentTimeMillis();


        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - START_TIME;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new WorkThread().start();
        new PrintThread().start();
    }

}
