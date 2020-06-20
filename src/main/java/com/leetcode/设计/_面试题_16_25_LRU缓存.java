package com.leetcode.设计;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * <p>
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * https://leetcode-cn.com/problems/lru-cache-lcci/
 * 题解：https://leetcode-cn.com/problems/lru-cache-lcci/solution/linkedhashmap-shuang-lian-biao-hashmap-dan-lian-2/
 */
public class _面试题_16_25_LRU缓存 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }


    public static class LRUCache {

        private LinkedList<Integer> queue = null;
        private Map<Integer, Integer> map = null;
        private int capacity = 0;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
            queue = new LinkedList<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;

            Integer value = map.get(key);

            queue.remove(queue.indexOf(key));
            queue.offerLast(key);

            return value;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                if (queue.size() + 1 > capacity) {
                    Integer poll = queue.pollFirst();
                    map.remove(poll);
                }
                map.put(key, value);
                queue.offerLast(key);
            } else {
                map.put(key, value);
                queue.remove(queue.indexOf(key));
                queue.offerLast(key);
            }
        }
    }
}