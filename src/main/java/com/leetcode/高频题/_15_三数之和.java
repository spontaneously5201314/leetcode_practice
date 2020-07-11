package com.leetcode.高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class _15_三数之和 {

    private int[] nums;

    public static void main(String[] args) {
        int[] nums = {-1, 5, 8, -6, 9, 7, 4, 3, 6, 5, 1, 2};
        _15_三数之和 v = new _15_三数之和();
        System.out.println(v.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        this.nums = nums;
        //排序
        sort(0, nums.length);
        System.out.println(Arrays.toString(nums));

        List<List<Integer>> list = new ArrayList<>();

        //设置三个变量
        int firstIndex = 0;
        for (; firstIndex < nums.length - 2; firstIndex++) {
            if (firstIndex > 0 && nums[firstIndex] == nums[firstIndex - 1]) continue;
            int secondIndex = firstIndex + 1, thirdIndex = nums.length - 1;
            while (secondIndex <= thirdIndex) {
                System.out.println("firstIndex: " + firstIndex + ", secondIndex: " + secondIndex + ", thirdIndex: " + thirdIndex);
                int sum = nums[firstIndex] + nums[secondIndex] + nums[thirdIndex];
                if (sum > 0) {
                    thirdIndex--;
                } else if (sum < 0) {
                    secondIndex++;
                } else {
                    list.add(Arrays.asList(nums[firstIndex], nums[secondIndex++], nums[thirdIndex--]));
                    //跳过相同值
                    while (secondIndex < thirdIndex && nums[secondIndex] == nums[secondIndex + 1]) secondIndex++;
                    while (secondIndex < thirdIndex && nums[thirdIndex] == nums[thirdIndex - 1]) thirdIndex--;
                }
            }
        }
        return list;
    }

    /**
     * 对[begin,end)范围内数字进行排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int pivot = pivotIndex(begin, end);
        sort(begin, pivot);
        sort(pivot + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        swap(begin, begin + new Random().nextInt(end - begin));
        int pivot = nums[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (nums[end] > pivot) {
                    end--;
                } else {
                    nums[begin++] = nums[end];
                    break;
                }
            }
            while (begin < end) {
                if (nums[begin] < pivot) {
                    begin++;
                } else {
                    nums[end--] = nums[begin];
                    break;
                }
            }
        }
        nums[begin] = pivot;
        return begin;
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
