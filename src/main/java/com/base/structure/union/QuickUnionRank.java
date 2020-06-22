package com.base.structure.union;

public class QuickUnionRank extends QuickUnion {

    private int[] ranks;

    public QuickUnionRank(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    @Override
    protected void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;

        if (ranks[parent1] < ranks[parent2]) {
            parents[parent1] = parent2;
        } else if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        }
    }
}
