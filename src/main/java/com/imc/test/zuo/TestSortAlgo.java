package com.imc.test.zuo;

/**
 * @author luoly
 * @date 2021/10/21 10:34
 * @description
 */
public class TestSortAlgo {
    public static void main(String[] args) {
        int[] arr = {3,1,2,5,8,6,4,7};

        printArr(arr);
//        choose(arr);
        bubble(arr);
//        insertion(arr);
        printArr(arr);
    }
    public static boolean unvalid(int[] arr) {
        return arr == null || arr.length<=1;
    }

    /**
     * 选择排序 外层取0-n，内层从n+1往0-n寻找并插入到正确的位置
     * @param arr
     */
    public static void insertion(int[] arr) {
        if(unvalid(arr)) {
            return;
        }
        for(int i=0; i<arr.length-1; i++) {
            for(int j=i+1; j>0 && arr[j] < arr[j-1]; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    /**
     * 冒泡
     * @param arr
     */
    public static void bubble(int[] arr) {
        if(unvalid(arr)) {
            return;
        }
        for(int i=0; i<arr.length; i++) {
            //第i趟排好序的个数是length-i，内层循环就不管了
            for(int j=i+1; j<arr.length - i; j++) {
                if(arr[j-1] > arr[j]) {
                    swap(arr, j-1, j);
                }
            }
        }
    }

    /**
     * 选择排序 关键点 内层循环在n+1到n之间找到最小值，定义临时变量和最小值交换位置
     * @param arr
     */
    public static void choose(int[] arr) {
        if(unvalid(arr)) {
            return;
        }
        //0~n
        //1~n比较并交换最小值
        int n = arr.length;
        for(int i=0; i<n; i++) {
            int minIdx = i;
            //遍历找出最小值下标
            for(int j=i+1; j<n; j++) {
                if(arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            //和选出的最小值交换
            swap(arr, i, minIdx);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
