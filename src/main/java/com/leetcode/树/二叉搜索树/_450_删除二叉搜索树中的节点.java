package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;
import com.structure.tree.printer.BinaryTrees;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 示例:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * <p>
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 *
 * @author 洪飞
 * @date 2020/6/11
 */
public class _450_删除二叉搜索树中的节点 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(7);

//        TreeNode root = new TreeNode(0);

        _450_删除二叉搜索树中的节点 v = new _450_删除二叉搜索树中的节点();
        v.remove(root, 3);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        remove(root, key);
        BinaryTrees.println(root);
        return root;
    }

    private TreeNode remove(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val) root.right = remove(root.right, key);
            // delete from the left subtree
        else if (key < root.val) root.left = remove(root.left, key);
            // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = remove(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = remove(root.left, root.val);
            }
        }
        return root;
    }

    private int successor(TreeNode node) {
        //找后继节点
        TreeNode right = node.right;
        while (right.left != null) {
            right = right.left;
        }
        return right.val;
    }

    private int predecessor(TreeNode node) {
        TreeNode left = node.left;
        while (left.right != null) {
            left = left.right;
        }
        return left.val;
    }
}
