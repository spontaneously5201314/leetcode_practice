package com.base.structure.union;

/**
 * 实现并查集，查找优先
 */
public class QuickFound extends UnionFound {

    public QuickFound(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;
        parents[v1] = parent2;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == parent1) {
                parents[i] = parent2;
            }
        }
    }
}
