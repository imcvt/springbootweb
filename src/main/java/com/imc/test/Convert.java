package com.imc.test;

import java.util.Stack;

/**
 * @author luoly
 * @date 2020/12/18 09:39
 * @description
 */
/*
 * 递归方法
 * */
public class Convert {

    // 双向链表的左边头结点和右边头节点​
    TreeNode leftHead = null;
    TreeNode rightHead = null;

    public TreeNode treeConvertListOfCircle(TreeNode root){

        // 递归调用叶子节点的左右节点返回null
        if (root == null)
            return null;

        // 第一次运行时，它会使最左边叶子节点为链表的第一个节点
        treeConvertListOfCircle(root.left);

        if (rightHead == null) {
            leftHead = root;
            rightHead = root;
        } else {
            // 把根节点插入到双向链表右边，rightHead向后移动
            rightHead.right = root;
            root.left = rightHead;
            rightHead = root;
            //循环之后的结果是，rightHead双向链表指向了当前root，然后左节点变成了(上一个rightHead的右边赋值了root)
        }

        // 把右叶子节点也插入到双向链表（rightHead已确定，直接插入）
         treeConvertListOfCircle(root.right);
        // 返回左边头结点
        return leftHead;
    }

    /*
     * 非递归方法
     * */
    //修改当前遍历节点与前一遍历节点的指针指向
    public TreeNode treeConvertListNoCircle(TreeNode root) {
        if(root==null)
            return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode r = root;
        TreeNode pre = null;        // 用于保存中序遍历序列的上一节点
        boolean flag = true;        //定义标记
        while(r!=null || !stack.isEmpty()) {
            while(r != null) {
                stack.push(r);
                r = r.left;
            }
            r = stack.pop();
            if(flag){
                root = r;// 将中序遍历序列中的第一个节点记为root
                pre = root;
                flag = false;
            }else{
                pre.right = r;
                r.left = pre;
                pre = r;
            }
            r = r.right;
        }
        return root;
    }
}
