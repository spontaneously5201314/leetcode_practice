package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * @author 洪飞
 * @date 2020/6/9
 */
public class _114_二叉树展开为链表 {

    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode newRoot = null;
        List<Integer> list = preorder(root);
        Collections.reverse(list);
        for (Integer val : list) {
            TreeNode node = new TreeNode(val);
            node.right = newRoot;
            newRoot = node;
        }
        root = newRoot;
    }

    private List<Integer> preorder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        preorder(node, list);
        return list;
    }

    private void preorder(TreeNode node, List<Integer> list) {
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
