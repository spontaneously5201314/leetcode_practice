package com.leetcode.动态规划;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 */
public class _64_最小路径和 {

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) continue;
                else if (row == 0) grid[row][col] = grid[row][col - 1] + grid[row][col];
                else if (col == 0) grid[row][col] = grid[row - 1][col] + grid[row][col];
                else grid[row][col] = Math.min(grid[row - 1][col], grid[row][col - 1]) + grid[row][col];

            }
        }
        return grid[rows - 1][cols - 1];
    }

    public int minPathSumWitOne(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] dp = new int[cols];
        dp[0] = grid[0][0];

        for (int col = 1; col < cols; col++) {
            dp[col] = grid[0][col] + dp[col - 1];
        }

        for (int row = 1; row < rows; row++) {
            dp[0] = dp[0] + grid[row][0];
            for (int col = 1; col < cols; col++) {
                dp[col] = Math.min(dp[col - 1], dp[col]) + grid[row][col];
            }
        }
        return dp[cols - 1];
    }
}
