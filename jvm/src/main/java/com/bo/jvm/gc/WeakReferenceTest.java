package com.bo.jvm.gc;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author gpb
 * @date 2021/8/29 0:02
 */
public class WeakReferenceTest {

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
        new WeakHashMap<>();
        ThreadLocal local = new ThreadLocal();
        local.set(new WeakReferenceTest());
        new HashMap<>();
        // 建立一个弱引用
        WeakReference<User> userWeakReference = new WeakReference<>(new User(1,"张三"));
        System.out.println(userWeakReference.get());
        System.gc();
        System.out.println(userWeakReference.get());
    }

}
