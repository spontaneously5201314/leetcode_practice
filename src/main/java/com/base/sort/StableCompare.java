package com.base.sort;

/**
 * 用来测评一个排序算法是否是稳定排序
 * 测评标准是，相同元素，排序前和排序后必须是相同的位置
 */
public class StableCompare implements Comparable<StableCompare>{

    public int score;

    public int age;

    public StableCompare(int score, int age) {
        this.score = score;
        this.age = age;
    }

    @Override
    public int compareTo(StableCompare o) {
        return age - o.age;
    }
}
