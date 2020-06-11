package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

import java.util.Stack;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *  
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * @author 洪飞
 * @date 2020/6/11
 */
public class _530_二叉搜索树的最小绝对差 {

    int last = -1;
    int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (last == -1) {
            last = root.val;
        } else {
            min = Math.min(root.val - last, min);
            last = root.val;
        }
        inorder(root.right);
    }


    private int getMinimumDifferenceIter(TreeNode root) {
        Integer min = null;
        Integer lastVal = null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                stack.push(pop);
                stack.push(null);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            } else {
                TreeNode node = stack.pop();
                if (min == null) {
                    if (lastVal != null) {
                        min = node.val - lastVal;
                    }
                } else {
                    min = Math.min(node.val - lastVal, min);
                }
                lastVal = node.val;
            }
        }
        return min;
    }
}
