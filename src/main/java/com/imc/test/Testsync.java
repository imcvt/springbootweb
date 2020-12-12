package com.imc.test;

public class Testsync {
    static class Mai {
        int i=10;
        public void sup() {
            i --;
            System.out.println("i:"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Mai{
        public void sub() {
            try {
                while (i>0) {
                    i--;
                    System.out.println("i:" + i);
                    Thread.sleep(100);
                    sup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub s = new Sub();
                s.sub();
            }
        });
        t1.start();

    }
}
