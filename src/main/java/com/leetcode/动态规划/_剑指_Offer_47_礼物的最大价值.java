package com.leetcode.动态规划;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * 示例 1:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * <p>
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 */
public class _剑指_Offer_47_礼物的最大价值 {

    public int maxValue(int[][] grid) {
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
                dp[col] = Math.max(dp[col - 1], dp[col]) + grid[row][col];
            }
        }
        return dp[cols - 1];
    }

    public int maxValueWithTwo(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }
}
