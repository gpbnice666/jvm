package com.bo.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * 设置参数
 *          -Xms600m -Xmx600m -XX:+PrintGCDetails
 * @author gpb
 * @date 2021/8/14 19:49
 */
public class OOMTest {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }

}
class Picture{
    private byte[] pixls;

    public Picture(int length) {
        this.pixls = new byte[length];
    }
}