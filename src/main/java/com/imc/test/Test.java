package com.imc.test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author luoly
 * @date 2021/1/8 15:30
 * @description
 */
public class Test {
    private static Test in = new Test();
    public static int a;
    public static int b = 0;

    public Test() {
        a ++;
        b ++;
    }

    public static Test getInstance(){
        return in;
    }
    public static void main(String[] args) {
//        Test t = new Test();
//        System.out.println(t.a+",b="+t.b);
//        int k = 10;
//        int[] nums = {1,4,32,89,6,21,56,74,41,57,85,36,20};
//        System.out.println("test1= " + nums[0]);
//        quickSort(nums,10,20);
//        System.out.println("test3= " + nums[0]);

        System.out.println(t(1));
        //1
    }

    private static String t(int i) {
        try {
            System.out.println("try ===");
            if(i == 1)
                return "rturn 1";
            return "rrrturn";
        }catch (Exception e) {

        }finally {
            System.out.println("finlly");
        }
        return "456";
    }

    private static void quickSort(int[] sortNum, int startIndex, int endIndex)
    {
        int temp = 1;
        int[] test2 = {40,50,60};
//        sortNum = test2;
        sortNum[0] = 2;
        System.out.println("test2= " + sortNum[0]);
    }

    
}
