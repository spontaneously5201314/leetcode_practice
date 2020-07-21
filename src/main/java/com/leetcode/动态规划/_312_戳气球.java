package com.leetcode.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 */
public class _312_戳气球 {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        _312_戳气球 v = new _312_戳气球();
        // TODO: 2020/7/19 尚未做出来
        System.out.println(v.maxCoins(nums));
    }

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int length = nums.length;
        int sum = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
             System.out.println("i - 1 = " + (i - 1) + ", i = " + i + ", i + 1 = " + (i + 1));
            list.add(i, (i - 1 < 0 ? 1 : nums[i - 1]) * nums[i] * (i + 1 >= length - 1 ? 1 : nums[i + 1]));
        }

        int n = length;
        while (n-- > 0) {
            int tempMax = 0;
            int tempIndex = 0;
            for (int i = 0; i < length; i++) {
                if (list.get(i) >= 0 && list.get(i) > tempMax) {
                    tempIndex = i;
                }
            }
            sum += list.get(tempIndex);
            list.set(tempIndex, -1);

            //更换i前后的list
            list.add(tempIndex - 1, (tempIndex - 2 < 0 ? 1 : nums[tempIndex - 2]) * (tempIndex - 1 < 0 ? 1 : nums[tempIndex - 1]) * (tempIndex + 1 > length - 1 ? 1 : nums[tempIndex + 1]));
            System.out.println("tempIndex-1 = " + (tempIndex - 1) + ", tempIndex+1 = " + (tempIndex + 1) + ", tempIndex+2 = " + (tempIndex + 2));
            list.add(tempIndex + 1, (tempIndex - 1 < 0 ? 1 : nums[tempIndex - 1]) * (tempIndex + 1 >= length ? 1 : nums[tempIndex + 1]) * (tempIndex + 2 >= length - 2 ? 1 : nums[tempIndex + 2]));
        }
        return sum;
    }
}
