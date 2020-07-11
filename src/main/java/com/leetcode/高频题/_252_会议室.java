package com.leetcode.高频题;

import java.util.Arrays;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms
 */
public class _252_会议室 {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }
        return true;
    }
}
