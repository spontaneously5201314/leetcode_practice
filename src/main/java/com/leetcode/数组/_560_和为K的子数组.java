package com.leetcode.数组;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 *
 * @author 洪飞
 * @date 2020/6/17
 */
public class _560_和为K的子数组 {

    public static void main(String[] args) {
        int[] nums = {28, 54, 7, -70, 22, 65, -6};
        int k = 100;
        _560_和为K的子数组 k的子数组 = new _560_和为K的子数组();
        System.out.println(k的子数组.subarraySum(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        // TODO: 2020/6/17 不是最优解
        return subArraySum1(nums, k);
    }

    private int subArraySum1(int[] nums, int k) {
        int count = 0;
        //暴力解法
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                count++;
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
