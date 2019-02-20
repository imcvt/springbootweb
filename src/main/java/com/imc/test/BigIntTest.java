package com.imc.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luoly
 * @date 2019/1/8 11:24
 * @description
 */
public class BigIntTest {

    public static void main(String[] args) {
//        System.out.println(md5("~!@" + "100011486541" + "#$%" +"123456" + "^&*"));//E10ADC3949BA59ABBE56E057F20F883E E10ADC3949BA59ABBE56E057F20F883E
//        System.out.println("a".toUpperCase());
//        Map<String, String> map1 = new HashMap<>();
//        map1.put("SUPPLIERID", "100011557282");
//
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("100011557282", "57");
//        System.out.println(Integer.valueOf(map2.get(map1.get("SUPPLIERID"))));

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(12));
        System.out.println(String.valueOf(df.format(0.3)));


        DecimalFormat f1,f2;

        f1=new DecimalFormat("0.000");
        f2=new DecimalFormat("#.000");

        System.out.println("带0号的"+f1.format(0.20));
        System.out.println("带#的"+f2.format(0.20));

        f1=new DecimalFormat("0.00");
        f2=new DecimalFormat("0.##");

        System.out.println("小数点00："+f1.format(0.2));
        System.out.println("小数点##："+f2.format(0.2));


        f1=new DecimalFormat("0.00");
        f2=new DecimalFormat("##.00");

        System.out.println("00的整数："+f1.format(22));
        System.out.println("##的整数："+f2.format(22));
    }


    public static String md5(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes(), 0, str.length());
                String hashedPass = (new BigInteger(1, messageDigest.digest())).toString(16);
                if (hashedPass.length() < 32) {
                    hashedPass = "0" + hashedPass;
                }

                return hashedPass.toUpperCase();
            } catch (NoSuchAlgorithmException var3) {
                return null;
            }
        }
    }
}
