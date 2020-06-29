package com.leetcode.排序;

/**
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 * <p>
 * 示例：
 * <p>
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 * <p>
 * 0 <= len(array) <= 1000000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 */
public class _面试题_16_16_部分排序 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        _面试题_16_16_部分排序 v = new _面试题_16_16_部分排序();
        int[] subSort = v.subSort(array);
        System.out.println(subSort[0] + "_" + subSort[1]);
    }

    public int[] subSort(int[] array) {
        if (array == null || array.length < 2) return new int[]{-1, -1};
        //1.从左边扫描到右边，找到最后一个逆序对
        int max = array[0];
        int right = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                right = i;
            }
        }
        //2.从右边扫描到左边，找到最后一个逆序对
        int end = array.length - 1;
        int min = array[end];
        int left = -1;
        for (int i = end -1; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                left = i;
            }
        }
        return new int[]{left, right};
    }
}
