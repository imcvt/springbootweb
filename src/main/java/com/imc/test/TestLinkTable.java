package com.imc.test;

import java.sql.Array;
import java.util.ArrayList;

/**
 * 帮助理解，A=A+B,B=B+C,C=C+D,A=A+B+C+D,自身+调用自身的返回值
 */
public class TestLinkTable {

    static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void printListFromTailToHead(ListNode listNode) {
        arrayList.add(0, listNode.val);
        if(listNode.next != null) {
            printListFromTailToHead(listNode.next);
        }
        System.out.println(arrayList);
    }

    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if(listNode != null) arrayList.add(0, listNode.val);
        if(listNode.next != null) {
            printListFromTailToHead3(listNode.next);
        }
        return arrayList;
    }

    public static ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        if(listNode != null) arrayList.add(0, listNode.val);
        if(listNode.next != null) {
            printListFromTailToHead(listNode.next);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        printListFromTailToHead(l1);
    }

    public static void p(ListNode listNode) {
        if(listNode != null) arrayList.add(0, listNode.val);
        if(listNode.next != null) p(listNode.next);
    }


//if(listNode!=null){
//        printListFromTailToHead(listNode.next);
//        list.add(listNode.val);
//    }
//        return list;

//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        ListNode tmp = listNode.next;
//        while(tmp.next != null) {
//            arrayList.add(0, tmp.val);
//            tmp = tmp.next;
//        }
//        return arrayList;
//    }
}


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

