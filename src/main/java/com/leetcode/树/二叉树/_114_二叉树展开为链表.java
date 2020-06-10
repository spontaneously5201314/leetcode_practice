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
        // TODO: 2020/6/10 这个没有自己想到解法，看了题解
        // <url>https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/</url>
    }

    private void flattenIter(TreeNode node) {
        while (node != null) {
            if (node.left == null) {
                node = node.right;
            } else {
                //找到左子树的最右边节点，即前序遍历的前驱节点
                TreeNode pre = node.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点的友子节点上
                pre.right = node.left;
                //将左子树插入到右子树的地方
                node.right = node.left;
                //左子树置为空
                node.left = null;
                //节点移向它的下一个节点
                node = node.right;
            }
        }
    }

    private void flattenRecur(TreeNode node){
        if (node == null) return;

        TreeNode tempNode = null;
        if (node.right != null){
            tempNode = node.right;
            flatten(tempNode);
        }

        if (node.left != null){
            node.right = node.left;
            flatten(node.right);
        }

        while (node.right != null){
            node = node.right;
        }
        node.right = tempNode;
    }


    private List<Integer> preorder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        preorder(node, list);
        return list;
    }

    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
