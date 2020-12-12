package com.imc.test;

/**
 * 主线程和次线程的关系 ，主线程执行不完，其他线程会卡住
 */
public class TestThread2 {
    public static void main(String[] args) {
        Num n = new Num();
        Thread t1 = new Thread(n);
        t1.setName("A线程");

        Thread t2 = new Thread(n);
        t2.setName("B线程");

        t1.start();
        t2.start();
        int[][] array = {{1,2},{3,4}};
        System.out.println(find2(4, array));

    }

    public static boolean find2(int target, int [][] array) {
        int len = array.length;
        int max = array.length - 1;
        int temp = 0;
        int i = 0;
        while (temp <= array[max][max]) {
            if(temp == 0) temp = array[max][0];
            if(target < temp) {
                temp = array[max--][0];
            }else if(target > temp) {
                temp = array[max][i++];
            }else {
                return true;
            }

            if(max < 0 || i>len) break;
        }
        return false;
    }


}

class Num implements Runnable{
    int i = 1;
    @Override
    public void run() {
        synchronized (this){
            while (true) {
                notify();

                if(i>100) break;

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + ":" + i);

                try {
                    i++;
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
