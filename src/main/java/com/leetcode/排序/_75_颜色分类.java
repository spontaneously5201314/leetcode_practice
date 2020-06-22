package com.leetcode.排序;

import com.leetcode.ArrayUtils;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class _75_颜色分类 {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        _75_颜色分类 v = new _75_颜色分类();
        v.sortColors(nums);
        ArrayUtils.print(nums);
    }

    public void sortColors(int[] nums) {
//        sortCounting(nums);
        sortSwap(nums);
    }

    /**
     * 通过三个指针来解决，p0表示的是0元素的最右边的位置，p2表示的是2元素的最左边位置，curr表示当前指针的位置，初始为0
     * 当curr元素为2的时候，将p2和curr的位置交换
     * 当curr元素为0的时候，将p0和curr的位置交换
     *
     * @param nums
     */
    private void sortSwap(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }
    }

    private void sortCounting(int[] nums) {
        //使用计数排序
        //迭代计算出0、1、2每个元素的个数
        int[] result = new int[3];
        for (int i = 0; i < nums.length; i++) {
            result[nums[i]]++;
        }
        //按照0、1、2的排序重写当前数组
        int index = -1;
        for (int i = 0; i < result.length; i++) {
            while (result[i]-- > 0) {
                nums[++index] = i;
            }
        }
    }
}
