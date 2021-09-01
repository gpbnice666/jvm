package com.bo.jvm.methodArea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 元空间OOM测试
 * 参数
 *    -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * @author gpb
 * @date 2021/8/17 22:10
 */
public class MethodAreaOOM extends ClassLoader{

    public static void main(String[] args) {
        int j = 0;
        try {
            MethodAreaOOM methodAreaOOM = new MethodAreaOOM();
            for (int i = 0; i < 10000; i++) {
                // 创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_8, // 指定版本号
                        Opcodes.ACC_PUBLIC, // 权限修饰符
                        "Class" + i,    // 类名
                        null,           // 包名
                        "java/lang/Object" // 父类
                        ,null);   // 接口
                // 返回byte[]
                byte[] bytes = classWriter.toByteArray();
                // 类加载
                methodAreaOOM.defineClass("Class" + i,bytes,0,bytes.length);// Class对象
                j++;
            }

        }finally {
            System.out.println(j);
        }
    }
    /*
    * 3331
    Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:635)
        at com.bo.jvm.methodArea.MethodAreaOOM.main(MethodAreaOOM.java:31)
    *
    * */
}
