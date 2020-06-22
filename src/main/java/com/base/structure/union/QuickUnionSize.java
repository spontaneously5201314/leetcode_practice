package com.base.structure.union;

/**
 * Quick Union 基于size进行优化
 */
public class QuickUnionSize extends QuickUnion {

    private int[] sizes;

    public QuickUnionSize(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    protected void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;
        if (sizes[parent1] < sizes[parent2]) {
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        } else {
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        }
    }
}
