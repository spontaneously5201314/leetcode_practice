package com.leetcode.树.二叉树;

import com.alibaba.fastjson.JSON;
import com.leetcode.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * @author 洪飞
 * @date 2020/6/8
 */
public class _102_二叉树的层序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);

        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        root.right = right;

        _102_二叉树的层序遍历 v = new _102_二叉树的层序遍历();
        System.out.println(JSON.toJSONString(v.levelOrder(root)));
    }

    /**
     * 时间最优解
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list, root, 0);
        return list;
    }

    private void helper(List<List<Integer>> list, TreeNode node, int depth) {
        if (node == null) return;
        if (list.size() == depth) list.add(new LinkedList<>());
        list.get(depth).add(node.val);
        helper(list, node.left, depth + 1);
        helper(list, node.right, depth + 1);
    }


    /**
     * LeetCode最优解，这种方式相对于下面这种方式只是时间上稍微快一点
     * @param root 根节点
     * @return 返回结果
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                subList.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            list.add(subList);
        }
        return list;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> levels = new LinkedList<>();
        int currentLevel = 0;
        int parentNodes = 1;
        int childNodes = 0;
        levels.offer(root);
        list.add(currentLevel, new ArrayList<>());

        while (!levels.isEmpty()) {
            if (parentNodes > 0) {
                List<Integer> subList = list.get(currentLevel);
                if (subList == null) {
                    subList = new ArrayList<>();
                }
                TreeNode poll = levels.poll();
                subList.add(poll.val);
                list.set(currentLevel, subList);
                if (poll.left != null) {
                    levels.offer(poll.left);
                    childNodes++;
                }
                if (poll.right != null) {
                    levels.offer(poll.right);
                    childNodes++;
                }
                parentNodes--;
            }
            if (parentNodes == 0 && childNodes != 0) {
                parentNodes = childNodes;
                childNodes = 0;
                currentLevel++;
                List<Integer> subList = new ArrayList<>();
                list.add(currentLevel, subList);
            }
        }

        return list;
    }
}
