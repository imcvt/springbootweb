package com.imc.test;

import org.springframework.util.StringUtils;

import java.util.*;

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

        DoubleLinkNode node = new DoubleLinkNode("");
//        f(treeNode, node);
        f1(treeNode);
        Convert convert = new Convert();
        treeNode = convert.treeConvertListOfCircle(treeNode);

        printLinkNode(treeNode);

    }

    public static void printLinkNode(TreeNode node) {
        if(node == null)
            return;
        System.out.print(node.value+",");

        printLinkNode(node.right);
    }

    public static TreeNode init() {
        TreeNode t1 = new TreeNode("1");
        t1.left = new TreeNode("2");
        t1.right = new TreeNode("3");

        t1.left.left = new TreeNode("4");
        t1.left.right = new TreeNode("5");

        t1.right.left = new TreeNode("6");
        t1.right.right = new TreeNode("7");

        return t1;
    }

    //先：头左右  中：左头右 后：左右头
    public static void f(TreeNode treeNode, DoubleLinkNode node) {
        if(treeNode == null)
            return;

        System.out.print(treeNode.value);
        f(treeNode.left, node);
//        node.val = treeNode.value;
//        if(!StringUtils.isEmpty(preVal))
//        preVal = treeNode.value;
        f(treeNode.right, node);

    }

    //非递归中序遍历
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
