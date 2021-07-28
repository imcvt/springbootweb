package com.imc.test;

public class TestStackTrace {
    public static void main(String[] args) {
        String s = "";
        m1();
    }

    public static void m1() {
        //stacktrace数组代表当前线程的方法调用链，第一个是getStackTrace,第二个是m1(),第三个是main(),
        //由近及远
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getMethodName());
        }
    }
}
