package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.Stack;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 * 示例 ：
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 * <p>
 * 6
 * /   \
 * 3     5
 * \    /
 * 2  0
 * \
 * 1
 *  
 * 提示：
 * 给定的数组的大小在 [1, 1000] 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 */
@SuppressWarnings("unchecked")
public class _654_最大二叉树 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return findRoot(nums, 0, nums.length);
    }

    private TreeNode findRoot(int[] nums, int left, int right) {
        if (left == right) return null;

        int maxIdx = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums, left, maxIdx);
        root.right = findRoot(nums, maxIdx + 1, right);
        return root;
    }

    private int[] parentIndexes(int[] nums) {
        /**
         * 1、扫描一遍，保存所有元素
         * 2、保持栈从栈底到栈顶是单调递减的
         */
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lis[i] = -1;
            ris[i] = -1;
        }
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ris[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                lis[i] = stack.peek();
            }
            stack.push(i);
        }

        //生成父节点信息，是lis和ris中值最小的那个
        int[] pis = new int[nums.length];
        for (int i = 0; i < pis.length; i++) {
            if (lis[i] == -1 && ris[i] == -1) {
                pis[i] = -1;
                continue;
            }
            if (lis[i] == -1) pis[i] = ris[i];
            else if (ris[i] == -1) pis[i] = lis[i];
            else if (nums[lis[i]] < nums[ris[i]]) pis[i] = lis[i];
            else pis[i] = ris[i];
        }
        return pis;
    }
}
