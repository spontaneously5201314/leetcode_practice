package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 *
 * @author 洪飞
 * @date 2020/6/12
 */
public class _230_二叉搜索树中第K小的元素 {

    int n;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    public void inorder(TreeNode root, int k) {
        //剪枝
        if (root == null || n > k) return;
        inorder(root.left, k);
        n++;
        if (n == k) res = root.val;
        inorder(root.right, k);
    }

    public int kthSmallestWithIter(TreeNode root, int k) {
        if (root == null) return 0;
        int num = 1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (num++ == k) {
                return curr.val;
            }
            curr = curr.right;
        }
        return 0;
    }
}
