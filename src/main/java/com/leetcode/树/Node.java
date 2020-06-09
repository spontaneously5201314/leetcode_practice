package com.leetcode.树;

import java.util.List;

/**
 * N叉树的节点定义
 *
 * @author 洪飞
 * @date 2020/6/9
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
