package com.imc.test;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class TestStrLen {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcbabcAabadbbb"));//abcc
        System.out.println(getDifMaxLen("abcbabcAabadbbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    public static int getDifMaxLen(String s) {
        if(StringUtils.isEmpty(s))
            return 0;
        int len = s.length();
        int maxIndx = 0;
        for(int i=0;i<len;i++) {
            for(int j=i+1;j<len;j++) {
                Set<Character> set = new HashSet<>();
                int k0 = 1;
                for(int k=i;k<=j;k++) {
                    if(set.contains(s.charAt(k))) {
                        break;
                    }
                    k0 = k;
                    set.add(s.charAt(k));
                }
                maxIndx = Math.max(maxIndx, k0 - i + 1);
            }
        }

        return maxIndx;
    }
}

