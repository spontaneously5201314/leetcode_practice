package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 *
 * @author 洪飞
 * @date 2020/6/12
 */
public class _99_恢复二叉搜索树 {
    /**
     * 上一次中序遍历过的节点
     */
    private TreeNode prev;
    /**
     * 第一个错误节点
     */
    private TreeNode first;
    /**
     * 第二个错误节点
     */
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        findWrongNodes(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * 找到错误的两个节点，主要思想是利用中序遍历中会出现的逆序对来解决。
     */
    private void findWrongNodes(TreeNode root) {
        if (root == null) return;

        findWrongNodes(root.left);

        if (prev != null && prev.val > root.val) {
            //说明出现了逆序对
            second = root;

            if (first != null) return;
            first = prev;
        }
        prev = root;

        findWrongNodes(root.right);
    }
}
