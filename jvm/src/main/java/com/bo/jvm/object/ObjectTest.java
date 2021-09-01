package com.bo.jvm.object;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 对象创建击中方式，
 * @author gpb
 * @date 2021/8/21 17:32
 */
public class ObjectTest  implements Serializable,Cloneable{
    private int id;

    private String name;

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        // 1.使用new关键字
        ObjectTest objectTest = new ObjectTest();
        // 2.Class对象的newInstance()方法 无参数
        // 也可以通过  Class.forName("全限名"); 获取Class对象
        Class objectTestClass = ObjectTest.class;
        try {
            // ObjectTest objectTestClass = (ObjectTest)ObjectTest.class.newInstance();
            ObjectTest o = (ObjectTest )objectTestClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 3.构造函数newInstance() 有参
        try {
            Constructor<ObjectTest> constructor = ObjectTest.class.getConstructor();
            constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 4.序列化
        FileOutputStream fileOutputStream = new FileOutputStream("ObjectTest.out");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ObjectTest test = new ObjectTest();
        objectOutputStream.writeObject(test);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("ObjectTest.out");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ObjectTest object = (ObjectTest) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        // 5 重写Object类中的clone()方法
        ObjectTest clone = (ObjectTest) objectTest.clone();
        System.out.println(clone.toString());
        System.out.println(objectTest.toString());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj;
        obj = super.clone();
        return obj;
    }
}
