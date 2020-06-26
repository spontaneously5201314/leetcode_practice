package com.leetcode.动态规划;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 */
public class _322_零钱兑换 {

    public static void main(String[] args) {
        _322_零钱兑换 v = new _322_零钱兑换();
        System.out.println(v.coinChange(new int[]{1, 2, 5}, 11));
//        System.out.println(v.coinChange(new int[]{2}, 3));
    }

    public int coinChange(int[] coins, int amount) {
        if (amount <= 0 || coins == null || coins.length == 0) return -1;

//        int coinNums = coinChange1(coins, amount);
//        return coinNums == Integer.MIN_VALUE ? -1 : coinNums;

        return coinChange2(coins, amount);
    }

    /**
     * 解法二：记忆化搜索，因为暴力递归的过程中，会有很多次重复计算，可以将这些重复计算缓存起来，减少计算次数
     */
    private int coinChange2(int[] coins, int n) {
        if (n < 1 || coins == null || coins.length == 0) return -1;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) continue;
                int v = dp[i - coin];
                if (v < 0 || v >= min) continue;
                min = v;
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }

    private int coin(int[] coins, int n, int[] dp) {
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                min = Math.min(min, coin(coins, n - coin, dp));
            }
            dp[n] = min;
        }
        return dp[n];
    }

    /**
     * 解法一：暴力解法，递归
     */
    private int coinChange1(int[] coins, int n) {
        Arrays.sort(coins);
        int min = Integer.MAX_VALUE;
        if (n < 1) return min;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (n == coins[i]) {
                return 1;
            }
        }
        for (int i = coins.length - 1; i >= 0; i--) {
            min = Math.min(min, coinChange1(coins, n - coins[i]));
        }
        return min + 1;
    }
}
