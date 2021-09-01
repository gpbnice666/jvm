package com.bo.jvm.loader;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

public class CLassLoaderTest {

    public static void main(String[] args) {
        // 获取系统类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取扩展加载器 sun.misc.Launcher$ExtClassLoader@39a054a5
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        // 获取引导类加载器 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        System.out.println("**********************启动类加载器**********************");
        // 获取启动类加载器能加在Java文件的路径
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println("**********************扩展类加载器**********************");
        // 扩展类加载器加载Java文件的路径
        String property = System.getProperty("java.ext.dirs");
        System.out.println(property);

        System.out.println("**********************系统加载器**********************");
        // 我们自己编写的Java文件是由系统加载器来进行加载的
        System.out.println(CLassLoaderTest.class.getClassLoader());
    }
}
