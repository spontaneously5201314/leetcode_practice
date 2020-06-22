package com.base.structure.union;

/**
 * Quick Union -- 基于Rank优化 -- 路径分裂
 * 路径分裂：使路径上每个节点都指向其祖父节点
 */
public class QuickUnionRankPathSpliting extends QuickUnionRank {

    public QuickUnionRankPathSpliting(int capacity) {
        super(capacity);
    }

    @Override
    protected int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}