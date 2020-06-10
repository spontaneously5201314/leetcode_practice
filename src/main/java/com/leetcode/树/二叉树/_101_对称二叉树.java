package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;
import com.structure.list.List;
import com.structure.list.dynamicArray.ArrayList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * @author 洪飞
 * @date 2020/6/10
 */
public class _101_对称二叉树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(-42);
        root.right = new TreeNode(-42);

        root.left.right = new TreeNode(76);
        root.right.left = new TreeNode(76);

        root.left.right.right = new TreeNode(13);
        root.right.left.left = new TreeNode(13);

        _101_对称二叉树 v = new _101_对称二叉树();
        v.isSymmetric(root);
    }

    /**
     * 主要逻辑是对比前序和后序的结果是否是对称的
     */

    public boolean isSymmetric(TreeNode root) {
//        return isSymmetricRecur(root, root);
        boolean symmetricIter = isSymmetricIter(root, root);
        System.out.println(symmetricIter);
        return symmetricIter;
    }

    /**
     * O(n)复杂度
     */
    private boolean isSymmetricRecur(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) return true;
        if (leftNode == null || rightNode == null) return false;
        return leftNode.val == rightNode.val && isSymmetricRecur(leftNode.left, rightNode.right) && isSymmetricRecur(leftNode.right, rightNode.left);
    }

    /**
     * O(n)复杂度
     */
    private boolean isSymmetricIter(TreeNode leftNode, TreeNode rightNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(leftNode);
        queue.offer(rightNode);

        while (!queue.isEmpty()) {
            leftNode = queue.poll();
            rightNode = queue.poll();

            if (leftNode == null && rightNode == null) continue;
            if ((leftNode == null || rightNode == null) || leftNode.val != rightNode.val) return false;

            queue.offer(leftNode.left);
            queue.offer(rightNode.right);

            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }
        return true;
    }
}
