package com.leetcode.树.二叉树;

import com.leetcode.树.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * 返回其后序遍历: [5,6,3,2,4,1].
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 *
 * @author 洪飞
 * @date 2020/6/9
 */
public class _590_N叉树的后序遍历 {

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        //递归
        postorderRecur(root, list);
        //迭代
        postorderIter(root, list);
        return list;
    }

    private void postorderRecur(Node node, List<Integer> list) {
        if (node == null) return;

        List<Node> children = node.children;
        if (!children.isEmpty()) {
            for (Node child : children) {
                postorderRecur(child, list);
            }
        }
        list.add(node.val);
    }

    private void postorderIter(Node node, List<Integer> list) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop != null) {
                stack.push(pop);
                stack.push(null);
                List<Node> children = pop.children;
                Collections.reverse(children);
                if (!children.isEmpty()) {
                    for (Node child : children) {
                        stack.push(child);
                    }
                }
            } else {
                list.add(stack.pop().val);
            }
        }
    }
}
