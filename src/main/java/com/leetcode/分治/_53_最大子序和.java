package com.leetcode.分治;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class _53_最大子序和 {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        _53_最大子序和 v = new _53_最大子序和();
        System.out.println(v.maxSubArray2(nums));
    }

    /**
     * 因为{@link _53_最大子序和#maxSubArray1(int[])}方法需要一个数组来存放每个dp的值，但是每次求值只需要用到上一个dp的值就行，所以不需要数组，用一个数字来计数即可
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp += nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
//        return maxSubArray(nums, 0, nums.length);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    private void maxSubArray(int i, int[] nums, int[] dp) {

    }

    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;

        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(leftMax + rightMax, Math.max(maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end)));
    }
}
