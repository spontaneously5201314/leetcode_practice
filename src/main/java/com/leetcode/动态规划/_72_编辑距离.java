package com.leetcode.动态规划;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 */
public class _72_编辑距离 {

    /**
     * 从word1转换成word2
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        dp[0][0] = 0;
        for (int row = 1; row < dp.length; row++) {
            dp[row][0] = row;
        }
        for (int col = 1; col < dp[0].length; col++) {
            dp[0][col] = col;
        }

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[0].length; col++) {
                if (chars1[row - 1] == chars2[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                }
                dp[row][col] = Math.min(dp[row][col], Math.min(dp[row - 1][col] + 1, dp[row][col - 1] + 1));
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
