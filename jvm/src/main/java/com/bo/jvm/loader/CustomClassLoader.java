package com.bo.jvm.loader;

import java.io.FileNotFoundException;

/**
 * @author gpb
 * @date 2021/7/21 21:43
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if(result == null){
                throw new FileNotFoundException();
            }else{
                return defineClass(name,result,0,result.length);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类 。。。
        // 如果指定路径字节码文件进行加密,而需要在此地方进行解密
        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader userClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("One", true, userClassLoader);

            Object o = clazz.newInstance();

            System.out.println(o.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
