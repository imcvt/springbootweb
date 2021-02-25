package com.imc.test;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luoly
 * @date 2020/12/15 09:11
 * @description
 */
public class TreeNodeTest {
    static String preVal;
    static String afVal;
    public static void main(String[] args) {
        TreeNode treeNode = init();
//pp1(treeNode);
        f(treeNode);
//        f1(treeNode);
//        Convert convert = new Convert();
//        treeNode = convert.treeConvertListOfCircle(treeNode);

//        inOrderBSTree(treeNode);
//        printLinkNode(treeNode);

    }

    public static void printLinkNode(TreeNode node) {
        if(node == null)
            return;
        System.out.print(node.value+",");

        printLinkNode(node.right);
    }

    //数组构造二叉树[4,2,5,1,3,6,7]
    public static TreeNode arrayToTree(String[] arr,int start,int end){
        //{"8","4","9","2","10","5","11","1","12","6","13","3","14","7","15"}
        TreeNode root = null;
        if(end >= start){
            root = new TreeNode("");
            int mid = (start + end + 1)/2;
            //二叉树根节点为数组中间的元素
            root.value = arr[mid];//arr[3]=1
            //递归方法用左半部分数组构造root的左子树[1],0, 2
            root.left = arrayToTree(arr,start,mid-1);
            //递归方法用右半部分数组构造root的右子树
            root.right = arrayToTree(arr,mid+1,end);
        }else{
            root = null;
        }
        return root;
    }

    //双向链表头结点
    private static TreeNode pHead = null;
    //双向链表尾结点
    private static TreeNode pEnd = null;
    public static void inOrderBSTree(TreeNode root){
        if(root == null){
            return;
        }
        //转换root的左子树
        inOrderBSTree(root.left);
        //使当前结点的左孩子指向双向链表中最后一个结点
        root.left = pEnd;
        //双向链表为空，当前遍历的结点为双向链表的头结点
        if(pEnd == null){
            pHead = root;
        }else {
            //使双向链表中最后一个结点的右孩子指向当前结点
            pEnd.right = root;
        }
        //将当前结点设为双向链表中最后一个结点
        pEnd = root;
        //转换root的右子树
        inOrderBSTree(root.right);
    }

    public static void pp(TreeNode treeNode) {
        if(treeNode == null)
            return;
        if(treeNode.left != null)
            pp(treeNode.left);
        System.out.print(treeNode.value);
        if(treeNode.right != null)
            pp(treeNode.right);
    }

    public static void pp1(TreeNode treeNode) {
        //1.对象引用temp的值
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = treeNode;
        while (temp != null || !stack.isEmpty()){
            if(temp != null) {
                stack.push(temp);
                temp = temp.left;
            }else {
                temp = stack.pop();
                System.out.print(temp.value);
                temp = temp.right;
            }
        }
//        Hashtable h=  new Hashtable();
//        h.put()
    }

    public static TreeNode init() {
//        TreeNode t1 = new TreeNode("1");
//        t1.left = new TreeNode("2");
//        t1.right = new TreeNode("3");
//
//        t1.left.left = new TreeNode("4");
//        t1.left.right = new TreeNode("5");
//
//        t1.right.left = new TreeNode("6");
//        t1.right.right = new TreeNode("7");
//
//        return t1;
//        String s[] = {"4","2","5","1","6","3","7"};
        String s[] = {"8","4","9","2","10","5","11","1","12","6","13","3","14","7","15"};
        return arrayToTree(s, 0, s.length-1);
    }

    //先：头左右  中：左头右 后：左右头
    public static void f(TreeNode treeNode) {
        if(treeNode == null)
            return;

        System.out.print(treeNode.value);
        f(treeNode.left);
//        node.val = treeNode.value;
//        if(!StringUtils.isEmpty(preVal))
//        preVal = treeNode.value;
        f(treeNode.right);

    }

    //非递归中序遍历4 2 5 1 6 3 7
    public List<Integer> f0(TreeNode head) {
         List<Integer> list=new ArrayList<Integer>();
         Stack<TreeNode> stack=new Stack<TreeNode>();
         if (head!=null) {
             while(head!=null||!stack.empty()) {
                 if(head!=null) {
                     stack.push(head);
                     head=head.left;
                 }else {
                     head=stack.pop();
                     System.out.println(head.value);
                     head=head.right;
                 }
             }
         }
         return list;
     }

     //非递归先序遍历
    public static void f1(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if(treeNode == null)
            return;
        stack.push(treeNode);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.value);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    //非递归先序遍历
    public static void f2(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode r = treeNode;
        while (r != null || !stack.isEmpty()) {
            while (r != null) {
                stack.push(r);
                r = r.left;
            }
            r = stack.pop();

            System.out.println(r.value);

            r = r.right;
        }
    }

    //非递归后序遍历 1,3,7,6,2,5,4；反过来输出即可
    public static void back(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            stack1.push(node);
            if(node.left != null)
                stack1.push(node.left);
            if(node.right != null)
                stack1.push(node.right);
        }
    }

    //非递归平行打印二叉树
    public static void parell(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {

            TreeNode poll = queue.poll();
            System.out.print(poll.value+"=>");
            if(poll.left != null)
                queue.add(poll.left);

            if(poll.right != null)
                queue.add(poll.right);
        }
    }
}

class TreeNode{
    public TreeNode(String value) {
        this.value = value;
    }
    String value;
    TreeNode left;
    TreeNode right;
}

class DoubleLinkNode{
    String val;
    public DoubleLinkNode(String val) {
        this.val = val;
    }

    DoubleLinkNode pre;
    DoubleLinkNode after;
}
