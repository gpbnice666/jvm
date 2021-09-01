package com.bo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.Date;

/**
 * @author gpb
 * @date 2021/8/4 22:43
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Externalizable {
    private Integer id;
    private String name;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        User user = (User) in.readObject();
        System.out.println(user);
    }

    /**
     * 序列化
     * @param fileName
     * @throws IOException
     */
    public static void serialize(String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));

        objectOutputStream.writeObject("序列化的日期：");
        objectOutputStream.writeObject(new Date());
        User user = new User(1,"哈哈");
        objectOutputStream.writeObject(user);

        objectOutputStream.close();

        String s1 = "ab";
        System.out.println(s1.intern());
    }
    /**
     * 反序列化
     * @param fileName
     * @throws IOException
     */
    public static void deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        String str = (String) objectInputStream.readObject();
        Date date=(Date) objectInputStream.readObject();//日期对象
        User userInfo=(User) objectInputStream.readObject();//会员对象
        System.out.println(str);
        System.out.println(date);
        System.out.println(userInfo);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize("text");
        deserialize("text");
    }

    /**
     public static void main(String[] args) throws Exception {
        User user = new User();
        // 创建一个对象输出流，它可以包装一个其他类型的目标输出流，如文件输出流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\object.out"));
        user.setId(1);
        user.setName("掌声");
        // 序列化
        objectOutputStream.writeObject(user);
        // 创建一个对象输入流，它可以包装一个其它类型输入流，如文件输入流：
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("D:\\object.out")));
        // 发序列化
        User users = (User) objectInputStream.readObject();
        System.out.println(users);  // User(id=1, name=掌声)
    }
    */
}
