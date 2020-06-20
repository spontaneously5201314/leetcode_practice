package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.*;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 */
public class _199_二叉树的右视图 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        _199_二叉树的右视图 v = new _199_二叉树的右视图();
        System.out.println(v.rightSideView(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (size == 1 && poll != null) result.add(poll.val);
            size--;
            if (poll != null && poll.left != null) queue.offer(poll.left);
            if (poll != null && poll.right != null) queue.offer(poll.right);
            if (size == 0) size = queue.size();
        }
        return result;
    }
}
