package com.leetcode.高频题;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms-ii
 */
public class _253_会议室_II {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        //存放所有会议的开始时间
        int[] begins = new int[intervals.length];
        //存放所有会议的结束时间
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        //排序
        Arrays.sort(begins);
        Arrays.sort(ends);

        int room = 0, endIndex = 0;
        for (int begin : begins) {
            if (begin >= ends[endIndex]) {
                //能够重复利用一个会议室
                endIndex++;
            } else {
                //需要新开一个会议室
                room++;
            }
        }
        return room;
    }


    public int minMeetingRoomsWithHeap(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);
        //创建最小堆，堆中存放的元素是每场会议的结束时间
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //添加第一场会议的结束时间
        heap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            //第i号会议的开始时间 大于 堆顶，说明有空的会议室可用，将堆顶移除，并加入新的会议的结束时间
            if (intervals[i][0] >= heap.peek()) {
                heap.remove();
            }
            heap.add(intervals[i][1]);
        }
        //最终保留在堆中的元素的个数就是总共占用的会议室的最小个数
        return heap.size();
    }
}
