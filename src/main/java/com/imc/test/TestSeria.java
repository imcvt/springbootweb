package com.imc.test;

import java.io.*;

/**
 * @author luoly
 * @date 2021/4/23 16:25
 * @description 对象的序列化
 */
public class TestSeria {
    public static void main(String[] args) throws Exception{
//        write();
        bytearrWrite();
    }

    public static void write() throws Exception{
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Sales\\Desktop\\aaa.txt");
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Sales\\Desktop\\aaa.txt");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(new BeanIns("a", "b"));

        FileInputStream inputStream = new FileInputStream("C:\\Users\\Sales\\Desktop\\aaa.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        BeanIns beanIns = (BeanIns)objectInputStream.readObject();
        System.out.println(beanIns.getS1());
    }

    public static void bytearrWrite() throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(new BeanIns("a", "b"));

        System.out.println(byteArrayOutputStream);
    }
}
