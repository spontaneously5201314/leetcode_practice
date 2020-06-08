package com.leetcode.树.二叉树;

import com.alibaba.fastjson.JSON;
import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * @author 洪飞
 * @date 2020/6/8
 */
public class _145_二叉树的后序遍历 {

    public static void main(String[] args) {
        _145_二叉树的后序遍历 v = new _145_二叉树的后序遍历();
        v.test();
    }

    public void test(){
        List<Integer> result = new ArrayList<>();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        postorderTraversal(root);
        System.out.println(JSON.toJSONString(result));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        //迭代
//        result = postorderTraversalWithIter(root);

        //递归
        postorderTraversalWithRecur(root, result);

        return result;
    }

    private void postorderTraversalWithRecur(TreeNode node, List<Integer> result) {
        if (node == null) return;

        if (node.left != null) {
            postorderTraversalWithRecur(node.left, result);
        }
        if (node.right != null) {
            postorderTraversalWithRecur(node.right, result);
        }
        result.add(node.val);
    }

    private List<Integer> postorderTraversalWithIter(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }
}
