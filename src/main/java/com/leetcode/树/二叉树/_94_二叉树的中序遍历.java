package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * @author 洪飞
 * @date 2020/6/8
 */
public class _94_二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        //递归
        inorderTraversalWithRecur(root, result);

        //迭代
        inorderTraversalWithIter(root, result);

        return result;
    }

    private void inorderTraversalWithIter(TreeNode node, List<Integer> result) {
        if (node == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
    }

    private void inorderTraversalWithRecur(TreeNode node, List<Integer> result) {
        if (node == null) return;

        inorderTraversalWithRecur(node.left, result);
        result.add(node.val);
        inorderTraversalWithRecur(node.right, result);
    }

}
