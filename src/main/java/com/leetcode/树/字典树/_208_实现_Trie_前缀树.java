package com.leetcode.树.字典树;


import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 *
 * @author 洪飞
 * @date 2020/6/20
 */
public class _208_实现_Trie_前缀树 {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        //true
        System.out.println(trie.search("apple"));
        //false
        System.out.println(trie.search("app"));
        //true
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        //true
        System.out.println(trie.search("app"));
    }
}

/**
 * 高效率的字典树
 */
class HighSpeedTrie {
    Node head = new Node();

    /**
     * Initialize your data structure here.
     */
    public HighSpeedTrie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node currNode = head;
        for (int ii = 0; ii < word.length(); ii++) {
            int idx = word.charAt(ii) - 'a';
            if (currNode.nodes[idx] == null) {
                currNode.nodes[idx] = new Node();
            }
            currNode = currNode.nodes[idx];
        }
        currNode.end = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node currNode = head;
        for (int ii = 0; ii < word.length(); ii++) {
            int idx = word.charAt(ii) - 'a';
            if (currNode.nodes[idx] == null) {
                return false;
            } else {
                currNode = currNode.nodes[idx];
            }
        }
        return (currNode.end == true);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node currNode = head;
        for (int ii = 0; ii < prefix.length(); ii++) {
            int idx = prefix.charAt(ii) - 'a';
            if (currNode.nodes[idx] == null) {
                return false;
            } else {
                currNode = currNode.nodes[idx];
            }
        }
        return true;
    }

    public class Node {
        Node[] nodes = new Node[26];
        boolean end = false;
    }
}

class Trie {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if ("".equals(word) || word.length() == 0) return;

        int length = word.length();
        TrieNode currentNode = root;
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            Map<Character, TrieNode> children = currentNode.children;
            boolean containsKey = children.containsKey(c);
            if (containsKey) {
                //包含
                currentNode = children.get(c);
            } else {
                //不包含，创建一个新节点
                TrieNode trieNode = new TrieNode(c);
                children.put(c, trieNode);
                currentNode = children.get(c);
            }
            if (i == length - 1) {
                currentNode.wordEnd = true;
                currentNode.word = word;
            }
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (root.children == null || root.children.isEmpty() || "".equals(word)) return false;

        int length = word.length();
        TrieNode currentNode = root;
        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            HashMap<Character, TrieNode> children = currentNode.children;
            boolean containsKey = children.containsKey(c);
            if (!containsKey) return false;
            currentNode = children.get(c);
            if (i == length - 1) {
                return currentNode.wordEnd;
            }
        }
        return true;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (root.children == null || root.children.isEmpty()) return false;

        int length = prefix.length();
        TrieNode currentNode = root;
        for (int i = 0; i < length; i++) {
            char c = prefix.charAt(i);
            HashMap<Character, TrieNode> children = currentNode.children;
            boolean containsKey = children.containsKey(c);
            if (!containsKey) return false;
            else currentNode = children.get(c);
        }
        return true;
    }

    private class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean wordEnd = false;
        Character character;
        String word;

        public TrieNode() {
        }

        public TrieNode(Character character) {
            this.character = character;
        }
    }
}
