package com.imc.test;

/**
 * @author luoly
 * @date 2021/10/20 10:12
 * @description
 */
public class TestIntConvert {
    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        convert2chara(num);
    }

    /**
     * 打印输出2进制
     * @param num 10进制 对应的2进制格式000000000000001001100001111111000
     */
    public static void convert2chara(int num) {
        for(int i=31;i>=0;i--) {
            System.out.print((num & (long)(Math.pow(2,i))) == 0?0:1);
        }
    }
}
