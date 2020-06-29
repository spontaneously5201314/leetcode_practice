package com.leetcode.数组;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class _88_合并两个有序数组 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        _88_合并两个有序数组 v = new _88_合并两个有序数组();
        v.merge(nums1, m, nums2, n);
        System.out.println(nums1);
    }

    /**
     * 思路：从尾部开始，利用双指针向前遍历，添加最大的元素到nums1的末尾
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, current = m + n - 1;
        while (p2 >= 0) {
            if (p1 >= 0 && nums2[p2] < nums1[p1]) {
                nums1[current--] = nums1[p1--];
            } else {
                nums1[current--] = nums2[p2--];
            }
        }
    }
}
