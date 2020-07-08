package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class _46_全排列 {

    /**
     * 思路：直接使用nums数据作为存放中间变量的方法
     * 在第0层，将nums[0]分别与0，1，2位置上的数组进行交换
     * 在第1层，将nums[11]分别与1，2位置上的数组进行交换
     * 在第2层，将nums[2]分别与2位置上的数组进行交换
     */

    private List<List<Integer>> list;
    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;
        this.nums = nums;
        dfs(0);

        return list;
    }

    private void dfs(int index) {
        if (index == nums.length) {
            List<Integer> subList = new ArrayList<>();
            for (int value : nums) {
                subList.add(value);
            }
            list.add(subList);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(index, i);
            dfs(index + 1);
            //还原现场
            swap(index, i);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
