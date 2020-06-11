package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * @author 洪飞
 * @date 2020/6/11
 */
public class _108_将有序数组转换为二叉搜索树 {

    public TreeNode sortedArrayToBST(int[] nums) {
        // TODO: 2020/6/11 尚未完成，明天继续
        return insertTreeNode(nums, 0, nums.length);
    }

    private TreeNode insertTreeNode(int[] nums, int start, int end) {
        int middleIndex = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middleIndex]);

        root.left = insertTreeNode(nums, start, middleIndex - 1);
        root.right = insertTreeNode(nums, middleIndex + 1, end);

        return root;
    }
}
