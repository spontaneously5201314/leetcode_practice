package com.base.structure.union;

/**
 * Quick Union -- 基于Rank优化 -- 路径压缩
 */
public class QuickUnionRankPathCompression extends QuickUnionRank {

    public QuickUnionRankPathCompression(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        rangeCheck(v);
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
