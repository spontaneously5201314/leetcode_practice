package com.leetcode;

/**
 * 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 *
 * @author 洪飞
 * @date 2020/5/25
 */
public class Num0004 {

    public static void main(String[] args) {
        int[] one = new int[]{1, 2};
        int[] two = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(one, two));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0.0;
    }
}
