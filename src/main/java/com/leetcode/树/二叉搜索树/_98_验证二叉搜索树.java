package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * @author 洪飞
 * @date 2020/6/11
 */
public class _98_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBSTRecur(root, null, null);
    }

    private boolean isValidBSTRecur(TreeNode node, Integer minValue, Integer maxValue) {
        if (node == null) return true;

        int val = node.val;
        if (minValue != null && val <= minValue) return false;
        if (maxValue != null && val >= maxValue) return false;

        if (!isValidBSTRecur(node.right, val, maxValue)) return false;
        if (!isValidBSTRecur(node.left, minValue, val)) return false;

        return true;
    }
}
