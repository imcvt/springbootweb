package com.imc.test;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        f(treeNode, node);

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
