package com.bo.jvm.gc;

import java.lang.ref.SoftReference;

/**
 * 软引用测试
 * 参数
 *  -XX:+PrintGCDetails -Xms10m -Xmx10m
 * @author gpb
 * @date 2021/8/28 23:48
 */
public class SoftReferenceTest {

    public static class User{
        public int id;
        public String name;

        public User(int id,String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 建立软引用
        SoftReference<User> userSoftReference = new SoftReference<User>(new User(1,"张三"));

        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("GC 之后");
        System.out.println(userSoftReference.get());
        try{
            // 让系统资源不够
            //byte[] arr = new byte[1024 * 1024 * 7];
            // 系统资源不够
            byte[] arr = new byte[1024 * 7168 - 635 * 1024];
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            // 在OOM之前，垃圾回收器会清除软引用
            System.out.println(userSoftReference.get());
        }
    }

}
