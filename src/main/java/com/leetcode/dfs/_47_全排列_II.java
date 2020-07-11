package com.leetcode.dfs;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class _47_全排列_II {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        _47_全排列_II v = new _47_全排列_II();
        System.out.println(JSON.toJSONString(v.permuteUnique(nums)));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        List<List<Integer>> list = new ArrayList<>();
        dfs(0, nums, list);
        return list;
    }

    private void dfs(int index, int[] nums, List<List<Integer>> list) {
        if (index == nums.length) {
            ArrayList<Integer> subList = new ArrayList<>();
            for (int num : nums) {
                subList.add(num);
            }
            list.add(subList);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (isRepeat(nums, index, i)) continue;
            swap(nums, index, i);
            dfs(index + 1, nums, list);
            swap(nums, index, i);
        }
    }

    private boolean isRepeat(int[] nums, int index, int i) {
        for (int j = index; j < i; j++) {
            if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
