package com.base.structure.union;

import com.base.util.TimeUtils;

public class QuickFoundTest {

    private static final int COUNT = 100000;

    public static void main(String[] args) {
//        test(new QuickFound(COUNT));
//        test(new QuickUnion(COUNT));
        test(new QuickUnionSize(COUNT));
        test(new QuickUnionRank(COUNT));
        test(new QuickUnionRankPathCompression(COUNT));
        test(new QuickUnionRankPathSpliting(COUNT));
        test(new QuickUnionRankPathHalving(COUNT));
    }

    private static void test(UnionFound unionFound) {
        TimeUtils.test(unionFound.getClass().getSimpleName(), () -> {
            for (int i = 0; i < COUNT; i++) {
                unionFound.union((int) (Math.random() * COUNT),
                        (int) (Math.random() * COUNT));
            }
            for (int i = 0; i < COUNT; i++) {
                unionFound.isSame((int) (Math.random() * COUNT),
                        (int) (Math.random() * COUNT));
            }
        });
    }
}