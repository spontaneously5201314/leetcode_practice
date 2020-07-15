package com.leetcode.数组;

/**
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
 */
public class _4_寻找两个正序数组的中位数 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return findMedian(nums2);
        if (nums2 == null || nums2.length == 0) return findMedian(nums1);

        int[] num = new int[nums2.length + nums1.length];
        int begin1 = 0, begin2 = 0;
        int end1 = nums1.length - 1, end2 = nums2.length - 1;
        int i = 0;
        while (begin1 <= end1 && begin2 <= end2) {
            if (nums1[begin1] > nums2[begin2]) {
                num[i++] = nums2[begin2++];
            } else {
                num[i++] = nums1[begin1++];
            }
        }
        if (begin1 <= end1) {
            for (; begin1 <= end1; begin1++) num[i++] = nums1[begin1];
        }
        if (begin2 <= end2) {
            for (; begin2 <= end2; begin2++) num[i++] = nums2[begin2];
        }
        return findMedian(num);
    }

    private double findMedian(int[] num) {
        int length = num.length;
        if ((length & 1) == 0) {
            return (double) (((double) (num[length >> 1]) + (double) (num[(length >> 1) - 1])) / 2.0);
        } else {
            return (double) (num[length >> 1]);
        }
    }
}
