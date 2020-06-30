package com.leetcode.栈;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 */
public class _84_柱状图中最大的矩形 {

    /**
     * 和下面的方法一样，还是使用单调栈，只是优化一下，看看耗时对比如何
     */
    public int[] nextGreaterElementPlus(int[] nums1, int[] nums2) {
        if (nums2 == null || nums2.length == 0) return new int[0];

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2 == null || nums2.length == 0) return new int[0];
        int[] ng = nextGreater2(nums2);
        System.out.println(Arrays.toString(ng));

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    int index = ng[j];
                    if (index == 0) {
                        result[i] = -1;
                    } else {
                        result[i] = nums2[index];
                    }

                }
            }
        }
        return result;
    }

    private int[] nextGreater2(int[] nums2) {
        if (nums2 == null || nums2.length == 0) return null;
        int[] result = new int[nums2.length];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                Integer top = stack.pop();
                result[top] = i;
            }
            stack.push(i);
        }
        return result;
    }
}
