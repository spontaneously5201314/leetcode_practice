package com.leetcode.数组;

import java.util.Arrays;
import java.util.Collections;

/**
 * 给你一个已经 排好序 的整数数组 nums 和整数 a、b、c。对于数组中的每一个数 x，计算函数值 f(x) = ax2 + bx + c，请将函数值产生的数组返回。
 * <p>
 * 要注意，返回的这个数组必须按照 升序排列，并且我们所期望的解法时间复杂度为 O(n)。
 * <p>
 * 示例 1：
 * <p>
 * 输入: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * 输出: [3,9,15,33]
 * 示例 2：
 * <p>
 * 输入: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 * 输出: [-23,-5,1,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-transformed-array
 */
public class _360_有序转化数组 {

    public static void main(String[] args) {
        _360_有序转化数组 v = new _360_有序转化数组();
//        int[] result = v.sortTransformedArray(new int[]{-4, -2, 2, 4}, 1, 3, 5);
        int[] result = v.sortTransformedArray2(new int[]{-4, -2, 2, 4}, -1, 3, 5);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }


    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int j = 0;
        while (j < nums.length && nums[j] < 0) {
            j++;
        }
        int i = j - 1;

        int[] result = new int[nums.length];
        int resultIndex = 0;
        while (i >= 0 && j < nums.length) {
            int resultI = a * nums[i] * nums[i] + b * nums[i] + c;
            int resultJ = a * nums[j] * nums[j] + b * nums[j] + c;
            if (resultI >= resultJ && a > 0) {
                result[resultIndex++] = resultJ;
                j++;
            } else {
                result[resultIndex++] = resultI;
                i--;
            }
        }
        while (i >= 0) {
            result[resultIndex++] = a * nums[i] * nums[i] + b * nums[i] + c;
            i--;
        }
        while (j < nums.length) {
            result[resultIndex++] = a * nums[j] * nums[j] + b * nums[j] + c;
            j++;
        }

        return result;
    }

    public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
        int length = nums.length;
        int left = 0, right = length - 1;
        int leftValue, rightValue;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            leftValue = a * nums[left] * nums[left] + b * nums[left] + c;
            rightValue = a * nums[right] * nums[right] + b * nums[right] + c;
            if (a > 0) {
                if (leftValue < rightValue) {
                    result[length - i - 1] = rightValue;
                    right--;
                } else {
                    result[length - i - 1] = leftValue;
                    left++;
                }
            } else {
                if (leftValue < rightValue) {
                    result[i] = leftValue;
                    left++;
                } else {
                    result[i] = rightValue;
                    right--;
                }
            }
        }
        return result;
    }

    public int[] sortTransformedArray1(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = a * nums[i] * nums[i] + b * nums[i] + c;
        }
        Arrays.sort(result);
        return result;
    }
}
