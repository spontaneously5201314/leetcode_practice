package com.leetcode.集合;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public class _349_两个数组的交集 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new TreeSet<>();
        for (int num1 : nums1) s1.add(num1);
        Set<Integer> s2 = new TreeSet<>();
        for (int num2 : nums2) s2.add(num2);
        if (s1.size() < s2.size()) return inter_section(s1, s2);
        else return inter_section(s2, s1);
    }

    private int[] inter_section(Set<Integer> s1, Set<Integer> s2) {
        int[] output = new int[s1.size()];
        int index = 0;
        for (Integer integer : s1) {
            if (s2.contains(integer)) output[index++] = integer;
        }
        return Arrays.copyOf(output, index);
    }
}
