package com.imc.test.sort;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author luoly
 * @date 2020/12/3 09:08
 * @description
 */
public class QuickSort {
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args){
        int[] a = {1,6,4,3,8,5,2,7};
        quicksort(a, 0, 7);
        System.out.println(Arrays.toString(a));

//        int b[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
//        quickSort2(a);
//        System.out.println(Arrays.toString(a));

        System.out.println(18%16);//2
        System.out.println(18%32);//18

    }

    public static void quicksort(int [] a,int left,int right)
    {
        int low=left;
        int high=right;
        //下面两句的顺序一定不能混，否则会产生数组越界！！！very important！！！
        if(low>high)//作为判断是否截止条件
            return;
        int k=a[low];//额外空间k，取最左侧的一个作为衡量，最后要求左侧都比它小，右侧都比它大。
        while(low<high)//这一轮要求把左侧小于a[low],右侧大于a[low]。
        {
            while(low<high&&a[high]>=k)//右侧找到第一个小于k的停止
            {
                high--;
            }
            //这样就找到第一个比它小的了
            a[low]=a[high];//放到low位置
            while(low<high&&a[low]<=k)//在low往右找到第一个大于k的，放到右侧a[high]位置
            {
                low++;
            }
            a[high]=a[low];
        }
        a[low]=k;//赋值然后左右递归分治求之
        quicksort(a, left, low-1);
        quicksort(a, low+1, right);
    }

    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序

    }

    public static void quickSort2(int a[]) {
        sort(a, 0, a.length - 1);
    }

}
