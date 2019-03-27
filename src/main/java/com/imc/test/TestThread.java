package com.imc.test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private synchronized int add() {
        return value ++;
    }

    /**
     * 定长线程池
     */
    public static void testAdd() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        TestThread t = new TestThread();
        while (true) {
            fixedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName()+ ":" + t.add());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testVolatile() {
        TestThread testThread = new TestThread();
        new Thread(() -> {
            System.out.println("线程1执行开始...");
//            try {
                testThread.setValue(10);
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("线程1执行结束...");
        }).start();

        new Thread(() -> {
            System.out.println("线程2执行开始...");
            System.out.println(testThread.getValue());
            System.out.println("线程2执行结束...");
        }).start();

    }

    public static void main(String[] args) {
        testVolatile();
        System.out.println(Arrays.asList(1,2,3));
    }
}
