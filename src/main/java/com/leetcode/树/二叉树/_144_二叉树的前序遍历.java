package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * @author 洪飞
 * @date 2020/6/8
 */
public class _144_二叉树的前序遍历 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        //递归
        preorderTraversalWithRecur(root, result);

        //迭代
        preorderTraversalWithIter(root, result);
        return result;
    }

    //递归
    private List<Integer> preorderTraversalWithRecur(TreeNode node, List<Integer> result) {
        if (node == null) return result;

        result.add(node.val);
        preorderTraversalWithRecur(node.left, result);
        preorderTraversalWithRecur(node.right, result);
        return result;
    }

    //迭代
    private List<Integer> preorderTraversalWithIter(TreeNode node, List<Integer> result) {
        if (node == null) return result;

        Stack<TreeNode> queue = new Stack<>();
        queue.push(node);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pop();
            result.add(treeNode.val);
            if (treeNode.right != null) {
                queue.push(treeNode.right);
            }
            if (treeNode.left != null) {
                queue.push(treeNode.left);
            }
        }
        return result;
    }
}
