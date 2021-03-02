package com.imc.test;

/**
 * @author luoly
 * @date 2021/3/2 14:11
 * @description //1、加载  2、连接（验证/准备，即分配内存/解析，把符号引用解析为直接引用） 3、初始化 4、使用 5、卸载
 */
public class Test1 {
    public static int k = 0;//1
    public static Test1 initOrder1 = new Test1("t1");
    public static Test1 initOrder2 = new Test1("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("实例块");
    }

    static {
        print("静态块");
    }

    public Test1(String str) {
        System.out.println("construct:" + (++k) + ":" + str + " i=" + i + " n=" + n);//1:t1 i=0 n=0  2:t2 i=1 n=1
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println("print:" + (++k) + ":" + str + initOrder2 + " i=" + i + " n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        Test1 initOrder = new Test1("init");
    }
}
