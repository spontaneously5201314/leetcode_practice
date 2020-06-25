package com.leetcode.树.字典树;

import com.alibaba.fastjson.JSON;
import lombok.val;

import java.util.*;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * <p>
 * 例如，
 * <p>
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * <p>
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 */
public class _386_字典序排数 {

    public static void main(String[] args) {
        _386_字典序排数 v = new _386_字典序排数();
        List<Integer> list = v.lexicalOrder(10);
        System.out.println(JSON.toJSONString(list));
    }

    public List<Integer> lexicalOrder(int n) {
        //解法一：使用字典树来解，主要用来练习字典树
        //return lexicalOrderWithTrie(n);
        //解法二：使用DFS，还没搞懂
        lexicalOrderDFS(n, 0, 0);
        return anslexicalOrder;
    }

    List<Integer> anslexicalOrder = new ArrayList<>();

    public void lexicalOrderDFS(int maxValue, int num, int start) {
        if (num > maxValue)
            return;

        if (num > 0)
            anslexicalOrder.add(num);

        for (int i = start > 0 ? 0 : 1; i <= 9; i++)
            lexicalOrderDFS(maxValue, num * 10 + i, start + 1);
    }

    /**
     * 使用字典树解决问题，主要用来练习字典树
     *
     * @param n 排序的最大的数字
     * @return 返回字典序排数
     */
    private List<Integer> lexicalOrderWithTrie(int n) {
        LexicalTrie trie = new LexicalTrie();
        for (int i = 1; i <= n; i++) {
            trie.add(i, i);
        }
        return trie.scan();
    }

    private static class LexicalTrie {
        private Node<Integer> root;

        public Integer add(Integer key, Integer num) {
            if (key == null || num == null) return -1;
            if (root == null) root = new Node<>();
            Node<Integer> node = root;
            Stack<Integer> stack = new Stack<>();
            while (key != 0) {
                stack.push(key % 10);
                key /= 10;
            }
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                boolean emptyChildren = node.children == null;
                Node<Integer> childNode = emptyChildren ? null : node.children.get(pop);
                if (childNode == null) {
                    childNode = new Node<>();
                    childNode.key = pop;
                    node.children = emptyChildren ? new HashMap<>() : node.children;
                    node.children.put(pop, childNode);
                }
                node = childNode;
            }
            if (node.isNum) {
                Integer oldValue = node.value;
                node.value = num;
                return oldValue;
            }
            node.isNum = true;
            node.value = num;
            return null;
        }

        public List<Integer> scan() {
            //遍历字典树，返回所有的int
            if (root == null) return Collections.emptyList();
            Node<Integer> node = root;
            ArrayList<Integer> list = new ArrayList<>();
            scanRecur(node, list);
            return list;
        }

        private void scanRecur(Node<Integer> beginNode, List<Integer> result) {
            if (beginNode == null) return;
            if (beginNode.value != null) result.add(beginNode.value);
            HashMap<Integer, Node<Integer>> children = beginNode.children;
            if (children == null) return;
            if (!children.isEmpty()) {
                for (Map.Entry<Integer, Node<Integer>> entry : children.entrySet()) {
                    Node<Integer> childNode = entry.getValue();
                    System.out.println(childNode.value);
                    scanRecur(childNode, result);
                }
            }
        }
    }

    private static class Node<Integer> {
        Node<Integer> parent;
        HashMap<Integer, Node<Integer>> children;
        Integer key;
        Integer value;
        boolean isNum;
    }
}
