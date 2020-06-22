package com.base.structure.union;

/**
 * Quick Union思路实现并查集
 */
public class QuickUnion extends UnionFound {

    public QuickUnion(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    @Override
    protected void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;
        parents[parent1] = parent2;
    }
}
