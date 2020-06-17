package com.leetcode.数组;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 *
 * @author 洪飞
 * @date 2020/6/15
 */
public class _54_螺旋矩阵 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{
                1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        _54_螺旋矩阵 v = new _54_螺旋矩阵();
        List<Integer> integers = v.spiralOrder(matrix);
        integers.forEach(System.out::println);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return Collections.emptyList();
        int m = matrix.length;
        int n = matrix[0].length;
        int num = m * n;
        List<Integer> list = new ArrayList<>(m * n);

        int left = 0;
        int right = matrix[0].length;
        int top = 0;
        int bottom = matrix.length;

        while (num > 0) {
            for (int i = left; i < right && num > 0; i++) {
                list.add(matrix[top][i]);
                num--;
            }
            top++;
            for (int i = top; i < bottom && num > 0; i++) {
                list.add(matrix[i][right - 1]);
                num--;
            }
            right--;
            for (int i = right - 1; i >= left && num > 0; i--) {
                list.add(matrix[bottom - 1][i]);
                num--;
            }
            bottom--;
            for (int i = bottom - 1; i >= top && num > 0; i--) {
                list.add(matrix[i][left]);
                num--;
            }
            left++;
        }
        return list;
    }
}
