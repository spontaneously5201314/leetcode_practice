package com.leetcode.数组;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 */
public class _977_有序数组的平方 {

    public static void main(String[] args) {
        int[] A = new int[]{-4, -1, 0, 3, 10};
        _977_有序数组的平方 v = new _977_有序数组的平方();
        int[] squares = v.sortedSquares(A);
        for (int i = 0; i < squares.length; i++) {
            System.out.println(squares[i]);
        }
    }

    public int[] sortedSquares(int[] A) {
        int after = 0;
        while (after < A.length && A[after] < 0) {
            after++;
        }
        int before = after - 1;

        int resultIndex = 0;
        int[] result = new int[A.length];
        while (before >= 0 && after < A.length) {
            if (A[before] * A[before] < A[after] * A[after]) {
                result[resultIndex++] = A[before] * A[before];
                before--;
            } else {
                result[resultIndex++] = A[after] * A[after];
                after++;
            }
        }

        while (before >= 0) {
            result[resultIndex++] = A[before] * A[before];
            before--;
        }

        while (after < A.length) {
            result[resultIndex++] = A[after] * A[after];
            after++;
        }

        return result;
    }
}
