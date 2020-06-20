package com.leetcode.树.二叉树;

import com.alibaba.fastjson.JSON;
import com.leetcode.树.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class _103_二叉树的锯齿形层次遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        _103_二叉树的锯齿形层次遍历 v = new _103_二叉树的锯齿形层次遍历();
        List<List<Integer>> lists = v.zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(JSON.toJSONString(list));
        }
    }

    private boolean left2Right = true;

    // TODO: 2020/6/19 未完全实现 
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int depth = 0;
        list.add(depth, new ArrayList<>());

        while (!queue.isEmpty()) {
            List<Integer> subList = list.get(depth);
            TreeNode poll = queue.poll();
            subList.add(poll.val);
            list.add(depth, subList);
            size--;
            if (!left2Right) {
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            } else {
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
            }

            if (size == 0) {
                size = queue.size();
                depth++;
                list.add(depth, new ArrayList<>());
                left2Right = !left2Right;
            }
        }
        return list;
    }
}
