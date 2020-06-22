package com.base.structure.union;

public abstract class UnionFound {

    protected int[] parents;

    public UnionFound(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找v所属的集合（即返回根节点）
     *
     * @param v 待查元素v
     * @return 返回其更节点
     */
    protected abstract int find(int v);

    /**
     * 将两个节点合并到一个集合中
     *
     * @param v1 待合并节点1
     * @param v2 待合并节点2
     */
    protected abstract void union(int v1, int v2);

    /**
     * 查看两个元素是否是同一个集合
     *
     * @param v1 待查元素1
     * @param v2 待查元素2
     * @return true表示是同一个集合，false表示不是同一个集合
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("out of range exception");
        }
    }
}
