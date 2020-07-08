package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，其中最大指的是子树节点数最多的。
 * <p>
 * 注意:
 * 子树必须包含其所有后代。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,5,15,1,8,null,7]
 * <p>
 * 10
 * / \
 * 5  15
 * / \   \
 * 1   8   7
 * <p>
 * 输出: 3
 * 解释: 高亮部分为最大的 BST 子树。
 * 返回值 3 在这个样例中为子树大小。
 * 进阶:
 * 你能想出用 O(n) 的时间复杂度解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-bst-subtree
 */
public class _333_最大BST子树 {

    public int largestBSTSubtree(TreeNode root) {
        return root == null ? 0 : getInfo(root).size;
    }

    /**
     * 返回以root为根节点的二叉树的最大BST子树信息
     */
    private Info getInfo(TreeNode root) {
        if (root == null) return null;

        Info li = getInfo(root.left);
        Info ri = getInfo(root.right);

        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;

        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max) {
            leftBstSize = li.size;
            min = li.min;
        }

        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.right && root.val < ri.min) {
            rightBstSize = ri.size;
            max = ri.max;
        }

        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new Info(root, 1 + leftBstSize + rightBstSize, max, min);
        }

        if (li != null && ri != null) return li.size > ri.size ? li : ri;

        return li != null ? li : ri;
    }

    /**
     * 最大BST子树信息
     */
    private static class Info {
        /**
         * 根节点
         */
        private TreeNode root;
        /**
         * 节点总数
         */
        private int size;
        /**
         * 最大值
         */
        private int max;
        /**
         * 最小值
         */
        private int min;

        public Info(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }
}
