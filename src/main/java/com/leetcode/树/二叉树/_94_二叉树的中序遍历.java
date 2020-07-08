package com.leetcode.树.二叉树;

import com.alibaba.fastjson.JSON;
import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public class _94_二叉树的中序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        _94_二叉树的中序遍历 v = new _94_二叉树的中序遍历();
        List<Integer> list = v.inorderTraversal(root);
        System.out.println(JSON.toJSONString(list));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        //递归
//        inorderTraversalWithRecur(root, result);

        //迭代
//        inorderTraversalWithIter(root, result);

        //Morris
        inorderTraversalWithMorris(root, result);

        return result;
    }

    private void inorderTraversalWithMorris(TreeNode node, List<Integer> result) {
        if (node == null) return;
        while (node != null) {
            if (node.left != null) {
                TreeNode p = node.left;
                while (p.right != null && p.right != node) {
                    p = p.right;
                }
                if (p.right == null) {
                    p.right = node;
                    node = node.left;
                } else {
                    result.add(p.right.val);
                    p.right = null;
                    node = node.right;
                }
            } else {
                result.add(node.val);
                node = node.right;
            }
        }
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
