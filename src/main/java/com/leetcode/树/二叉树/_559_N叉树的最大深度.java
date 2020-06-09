package com.leetcode.树.二叉树;

import com.leetcode.树.Node;
import javafx.util.Pair;

import java.util.*;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * 我们应返回其最大深度，3。
 * <p>
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 *
 * @author 洪飞
 * @date 2020/6/9
 */
public class _559_N叉树的最大深度 {

    public int maxDepth(Node root) {
        //递归
        return maxDepthRecur(root);
        //迭代
        // return maxDepthIter(root);
        //BFS
        // return maxDepthBFS(root);
    }

    private int maxDepthRecur(Node node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;
        int max = 0;
        for (Node child : node.children) {
            max = Math.max(max, maxDepthRecur(child) + 1);
        }
        return max;
    }

    private int maxDepthIter(Node node) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        if (node != null) {
            stack.add(new Pair<>(node, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            node = current.getKey();
            Integer currentValue = current.getValue();
            if (node != null) {
                depth = Math.max(depth, currentValue);
                for (Node child : node.children) {
                    stack.add(new Pair<>(child, currentValue + 1));
                }
            }
        }
        return depth;
    }

    private int maxDepthBFS(Node node) {
        if (node == null) return 0;

        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll != null) {
                    for (Node child : poll.children) {
                        if (child != null) {
                            queue.offer(child);
                        }
                    }
                }
            }
            depth++;
        }
        return depth;
    }
}
