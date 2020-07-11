package com.leetcode.高频题;

import java.util.Arrays;

public class _283_移动零 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 0, 0, 6, 0, 7, 0, 8, 0, 9};
        _283_移动零 v = new _283_移动零();
        v.moveZeros(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeros(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) return;
        int zeroNum = 0;
        for (int i = 0; i < length; i++) {
            if (zeroNum > 0) {
                nums[i - zeroNum] = nums[i];
            }
            if (nums[i] == 0) {
                zeroNum++;
            }
        }
        for (int i = length - zeroNum; i < length; i++) {
            nums[i] = 0;
        }
    }
}
