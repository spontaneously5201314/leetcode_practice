package com.leetcode.树.二叉树;

import com.leetcode.树.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * <p>
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 *
 * @author 洪飞
 * @date 2020/6/9
 */
public class _589_N叉树的前序遍历 {

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();

        //递归
        preorderRecur(root, list);
        //迭代
        perorderIter(root, list);
        return list;
    }

    private void perorderIter(Node node, List<Integer> list) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            list.add(pop.val);
            List<Node> children = pop.children;
            if (!children.isEmpty()) {
                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
    }

    private void preorderRecur(Node node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        if (!node.children.isEmpty()){
            for (int i = 0; i < node.children.size(); i++) {
                preorderRecur(node.children.get(i), list);
            }
        }
    }
}
