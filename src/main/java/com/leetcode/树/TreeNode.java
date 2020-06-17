package com.leetcode.树;

import com.structure.tree.printer.BinaryTreeInfo;

public class TreeNode implements BinaryTreeInfo {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public Object root() {
        return this;
    }

    @Override
    public Object left(Object node) {
        return (TreeNode) this.left;
    }

    @Override
    public Object right(Object node) {
        return (TreeNode) this.right;
    }

    @Override
    public Object string(Object node) {
        return val + "_" + left.val + "_" + right.val;
    }
}
