package com.base.structure.union;

/**
 * Quick Union -- 基于Rank优化 -- 路径减半
 * 路径减半：使路径上每隔一个节点就指向其祖父节点
 */
public class QuickUnionRankPathHalving extends QuickUnionRank {

    public QuickUnionRankPathHalving(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}