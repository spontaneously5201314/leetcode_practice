package com.interview.面试真题;

/**
 * OKCoin算法一：整数加一
 * 一个正整数用一个数组来表示，数组的每一个位置代表这个整数的一位，比如123表示为[1,2,3]
 * 问题：写一个函数，把这个整数加一，返回加一后的数组
 * 参见：https://leetcode-cn.com/problems/plus-one/
 */
public class AddOne {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3};
        int[] nums = new int[]{9, 9, 9, 9};
        AddOne addOne = new AddOne();
        int[] result = addOne.plusOne(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    int[] addOne(int[] nums) {
        int flag = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = nums[i] + flag;
            if (i == nums.length - 1) {
                sum += 1;
            }
            nums[i] = sum % 10;
            flag = sum / 10;
            if (i == 0 && flag == 1) {
                int[] result = new int[nums.length + 1];
                for (int j = 0; j < result.length; j++) {
                    if (j == 0) {
                        result[j] = flag;
                    } else {
                        result[j] = nums[j - 1];
                    }
                }
                return result;
            }
        }
        return nums;
    }
}
