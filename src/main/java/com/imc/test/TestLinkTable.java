package com.imc.test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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

    public static ListNode reverseLinkTb(ListNode listNode) {
        if(listNode == null || listNode.next == null)
            return listNode;
        ListNode pre = listNode,cur=listNode.next,temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        listNode.next = null;
        return pre;
    }

    public static ListNode reverseLinkTb1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        //1 2 3 4 5 null
        //1 2 3 4 null 4
        ListNode last = reverseLinkTb1(head.next);
        head.next.next = head;// 变成了 5 -> 4 但是4的指针仍然指向5 也就是双向的
        head.next = null;
        return last;
    }

    public static ListNode reverseLink02(ListNode listNode) {
        if(listNode == null || listNode.next == null)
            return listNode;

        ListNode last = reverseLink02(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;
        return last;
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
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
//        printListFromTailToHead(l1);
        ListNode res = reverseLinkTb1(l1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
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

