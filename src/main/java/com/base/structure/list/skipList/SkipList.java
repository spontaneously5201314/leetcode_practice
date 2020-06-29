package com.base.structure.list.skipList;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class SkipList<K, V> {

    /**
     * 跳表默认层级，参考redis的默认设置是32层
     */
    private static final Integer MAX_LEVEL = 32;

    /**
     * Redis默认随机层级的概率值
     */
    private static final double P = 0.25;

    private int size;

    private Node<K, V> first;

    /**
     * 有效层数
     */
    private int level;

    private Comparator<K> comparator;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V put(K key, V value) {
        keyCheck(key);

        Node<K, V> node = first;
        //保存每一层的前驱
        Node[] prevs = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                //节点是存在的，就需要用新值覆盖旧值
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            prevs[i] = node;
        }

        //生成随机层数
        int randomLevel = randomLevel();

        //创建新节点
        Node<K, V> newNode = new Node<>(key, value, randomLevel);

        //连接前驱和后继
        for (int i = 0; i < randomLevel; i++) {
            if (randomLevel > level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }

        size++;

        //更新层数
        level = Math.max(level, randomLevel);

        return null;
    }

    public V get(K key) {
        keyCheck(key);

        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) return node.nexts[i].value;
        }

        return null;
    }

    public V remove(K key) {
        keyCheck(key);

        Node<K, V> node = first;
        //保存每一层的前驱
        Node[] prevs = new Node[level];
        //判断要删除的元素是否存在
        boolean exists = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            prevs[i] = node;
            if (cmp == 0) exists = true;
        }
        if (!exists) return null;

        //需要被删除的节点
        Node<K, V> removedNode = node.nexts[0];

        //数量减少
        size--;

        //设置后继
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prevs[i].nexts[i] = removedNode.nexts[i];
        }

        //更新level
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removedNode.value;
    }

    private int randomLevel() {
        int level = 1;
        if (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private void keyCheck(K key) {
        if (key == null) throw new IllegalArgumentException("key can not be null");
    }

    private int compare(K k1, K k2) {
        return this.comparator != null ? comparator.compare(k1, k2) : ((Comparable<K>) k1).compareTo(k2);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }
    }
}
