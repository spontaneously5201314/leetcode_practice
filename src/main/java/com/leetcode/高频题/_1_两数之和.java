package com.leetcode.高频题;

import com.base.structure.map.HashMap;
import com.base.structure.map.Map;

public class _1_两数之和 {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index != null) return new int[]{index, i};
            map.put(nums[i], i);
        }
        return null;
    }
}
