package com.leetcode.设计;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 * @author 洪飞
 * @date 2020/6/12
 */
public class _146_LRU缓存机制 {
    public static void main(String[] args) {
        /* 缓存容量 */
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        // 返回  1
        System.out.println(cache.get(1));
        // 该操作会使得关键字 2 作废
        cache.put(3, 3);
        // 返回 -1 (未找到)
        System.out.println(cache.get(2));
        // 该操作会使得关键字 1 作废
        cache.put(4, 4);
        // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        // 返回  3
        System.out.println(cache.get(3));
        // 返回  4
        System.out.println(cache.get(4));
    }
}

/**
 * 为了实现get和put操作都是O(1)级别的，所以使用hash来实现快速的查找key
 * 为了实现删除最久未使用的数据，可以使用队列来实现
 */
class LRUCache {

    private Map<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            removeNode(node);
            addNodeAfterHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
            addNodeAfterHead(node);
            return;
        } else {
            if (map.size() == this.capacity) {
                //淘汰
                removeNode(map.remove(this.tail.prev.key));
            }
            map.put(key, node = new Node(key, value));
            addNodeAfterHead(node);
        }

    }

    private void addNodeAfterHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}