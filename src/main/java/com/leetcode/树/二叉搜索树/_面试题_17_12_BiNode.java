package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 提示：
 * 节点数量不会超过 100000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binode-lcci
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public class _面试题_17_12_BiNode {

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;

        TreeNode newNode = root;
        while (newNode != null){

        }




        TreeNode left = convertBiNode(newNode.left);
        if (left != null) {
            left.left.right = newNode;
            left.left = null;
        }

        TreeNode right = convertBiNode(newNode.right);
        if (right != null) {
            right.left = null;
            newNode.left.right = right;
        }
        return newNode;
    }
}
