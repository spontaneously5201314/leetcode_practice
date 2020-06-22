package com.leetcode.排序;

import java.util.Arrays;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 */
public class _57_插入区间 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        _57_插入区间 v = new _57_插入区间();
        int[][] insert = v.insert(intervals, newInterval);
        for (int i = 0; i < insert.length; i++) {
            for (int j = 0; j < insert[i].length; j++) {
                System.out.println(insert[0] + "__" + insert[1]);
            }
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // TODO: 2020/6/22 尚未找到好的解决方案
//        int[][] newIntervals = new int[intervals.length + 1][2];
//        System.arraycopy(intervals, 0, newIntervals, 0, intervals.length);
//        newIntervals[newIntervals.length - 1] = newInterval;
//
//        Arrays.sort(newIntervals, (v1, v2) -> v1[0] - v2[0]);
//
//        int[][] result = new int[newIntervals.length][2];
//        int index = -1;
//        for (int i = 0; i < newIntervals.length; i++) {
//            if (index == -1 || result[index][1] < newIntervals[i][0]) {
//                result[++index] = newIntervals[i];
//            } else {
//                result[index][1] = Math.max(result[index][1], intervals[i][1]);
//            }
//        }
//        return Arrays.copyOf(result, index);
        return null;
    }
}
