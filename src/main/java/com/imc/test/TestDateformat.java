package com.imc.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TestDateformat {
    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime); // 2019-11-20T15:04:29.017
//        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        String strDate=localDateTime.format(dtf);
//        System.out.println(strDate);
        tt();
    }

    public static void tt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String timeStr = localDateTime.format(formatter);
        System.out.println(timeStr);

//        for (int i=0; i<100; i++) {
//            ThreadLocalRandom current = ThreadLocalRandom.current();
//            System.out.println(current.nextInt(5));
//        }
String s = " select * from GT250AT3.GT250AT3.supplierbankacclly where id= \'"+123+"\' ";
        Instant instant = Instant.now();
        Date date = Date.from(instant);

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
    }
}
