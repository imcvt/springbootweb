package com.imc.test;

/**
 * 2个线程交替打印1-100
 */
public class TestJiaoti implements Runnable{
    int max;
    int min;
    public TestJiaoti(int max) {
        this.max = max;
    }
    @Override
    public void run() {
        for(; ;) {
            synchronized(this) {
                notify();
                if(min >= max)break;
                try {
                    System.out.println(Thread.currentThread().getName()+":"+ ++min);
                    wait();
                } catch (Exception e) {

                }
            }

        }
    }

    public static void main(String[] args) {
        TestJiaoti r = new TestJiaoti(100);
        new Thread(r, "thread1").start();
        new Thread(r, "thread2").start();
    }
}
