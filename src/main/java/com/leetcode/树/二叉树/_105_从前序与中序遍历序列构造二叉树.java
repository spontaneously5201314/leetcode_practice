package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * @author 洪飞
 * @date 2020/6/10
 */
public class _105_从前序与中序遍历序列构造二叉树 {

    int[] preorder;

    private Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        _105_从前序与中序遍历序列构造二叉树 v = new _105_从前序与中序遍历序列构造二叉树();
        v.buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        this.preorder = preorder;
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int preLeft, int preRight, int inLeft, int inRight) {
        if (preRight < preLeft || inRight < inLeft) return null;

        TreeNode root = new TreeNode(preorder[preLeft]);
        int index = map.get(preorder[preLeft]);

        root.left = buildTree(preLeft + 1, preLeft + index - inLeft, inLeft, index - 1);
        root.right = buildTree(preLeft + index - inLeft + 1, preRight, index + 1, inRight);

        return root;
    }
}
