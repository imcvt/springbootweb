package com.imc.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luoly
 * @date 2020/12/15 09:11
 * @description
 */
public class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode treeNode = init();

        f(treeNode);

        String a = "abcdfsdfadfasdfadfadf";
        System.out.println(a.hashCode());
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
    public static void f(TreeNode treeNode) {
        if(treeNode == null)
            return;

        System.out.println(treeNode.value);
        f(treeNode.left);
        f(treeNode.right);

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
