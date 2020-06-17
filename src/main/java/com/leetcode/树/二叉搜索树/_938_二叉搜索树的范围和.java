package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 *
 * 二叉搜索树保证具有唯一的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * 示例 2：
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 *  
 *
 * 提示：
 *
 * 树中的结点数量最多为 10000 个。
 * 最终的答案保证小于 2^31。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * @author 洪飞
 * @date 2020/6/12
 */
public class _938_二叉搜索树的范围和 {

    int L;
    int R;
    int sum = 0;

    public int rangeSumBST(TreeNode root, int L, int R) {
        this.L = L;
        this.R = R;
        inorder(root);
        return sum;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        if (node.val < L) return;
        if (node.val > R) return;

        inorder(node.left);
        if (node.val >= L && node.val <= R) {
            sum += node.val;
        }
        inorder(node.right);
    }
}
