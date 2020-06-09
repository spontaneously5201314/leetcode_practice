package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * @author 洪飞
 * @date 2020/6/9
 */
public class _107_二叉树的层次遍历_II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                subList.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            list.addFirst(subList);
        }
        return list;
    }

    /**
     * 时间最优解
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<>();
        helper(list, root, 0);
        return list;
    }

    private void helper(LinkedList<List<Integer>> list, TreeNode node, int depth) {
        if (node == null) return;
        if (list.size() == depth) list.addFirst(new LinkedList<>());
        list.get(depth).add(node.val);
        helper(list, node.left, depth + 1);
        helper(list, node.right, depth + 1);
    }
}
