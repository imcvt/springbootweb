package com.imc.test.sort;

import java.util.Arrays;

/**
 * @author luoly
 * @date 2020/12/10 12:03
 * @description
 */
public class QuickSort2 {
    /**
     * 拆分数组
     * @param arr   要拆分的数组
     * @param start 数组拆分的起始索引 （从0开始）
     * @param end   数组拆分的结束索引
     */
    public static int partArr(int[] arr, int start, int end) {

        //选取基准元素，这里我们以最后一个位置，作为基准

        int base = arr[end];
        //记录，比基准元素小的变量，这里我们假设要比较的元素都不小于基准元素，这样通过比较
        //就把小于基准元素的数据全部找到，n=start表示的就是默认没有小于基准元素。
        int n = start;

        //基准元素不参与遍历比较
        for (int i = start; i < end; i++) {
            if (arr[i] < base) {
                //将小于基准元素的放到基准的左边
                if (i != n)
                    exchangeE(arr, i, n);
                n++;
            }
        }
        //遍历完成之后，将基准元素放到应该的位置上
        exchangeE(arr, n, end);
        return n;
    }

    /**
     * 交换数组中指定位置的两个元素
     *
     * @param array
     * @param index1
     * @param index2
     */
    public static void exchangeE(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void recurPartiton(int[] arr,int start,int end){

        //递归调用的结束条件,开始要拆分的数组就剩下一个元素的时候
        if(end-start<1)
            return;
        int part = partArr(arr, start, end);
        //三种情况下的继续拆分
        if(part==start)
            recurPartiton(arr,part+1,end);
        else if(part ==end)
            recurPartiton(arr,start,end-1);
        else{
            recurPartiton(arr,start,part-1);
            recurPartiton(arr,part+1,end);
        }
    }


    public static void main(String[] args) {
        int[] arr = {9,8,7,3,4,1,2,3};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr){
        recurPartiton(arr,0,arr.length-1);
    }
}
