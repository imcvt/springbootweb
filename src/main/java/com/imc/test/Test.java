package com.imc.test;

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
        Test t = new Test();
        System.out.println(t.a+",b="+t.b);
//        int k = 10;
//        int[] nums = {1,4,32,89,6,21,56,74,41,57,85,36,20};
//        System.out.println("test1= " + nums[0]);
//        quickSort(nums,10,20);
//        System.out.println("test3= " + nums[0]);

        //1
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
