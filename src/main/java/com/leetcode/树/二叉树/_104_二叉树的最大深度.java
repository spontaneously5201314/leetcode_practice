package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * @author 洪飞
 * @date 2020/6/9
 */
public class _104_二叉树的最大深度 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);

        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        root.right = right;

        _104_二叉树的最大深度 v = new _104_二叉树的最大深度();
        System.out.println(v.maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        //递归
//        return maxDepthWithRecur(root);
        //迭代
        return maxDepthWithIter(root);
    }

    private int maxDepthWithRecur(TreeNode node) {
        if(node == null) return 0;

        int leftDepth = 0;
        if (node.left != null) {
            leftDepth = maxDepthWithRecur(node.left);
        }
        int rightDepth = 0;
        if (node.right != null) {
            rightDepth = maxDepthWithRecur(node.right);
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    private int maxDepthWithIter(TreeNode node) {
        if (node == null) return 0;

        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            level++;
        }
        return level;
    }
}
