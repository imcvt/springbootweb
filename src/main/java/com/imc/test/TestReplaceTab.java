package com.imc.test;

public class TestReplaceTab {
    public static void main(String[] args) {
        System.out.println(replace("We Are Happy"));
    }

    public static String replace(String str) {
        return str.replaceAll(" ", "%20");
    }
}
