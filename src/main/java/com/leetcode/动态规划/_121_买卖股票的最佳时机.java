package com.leetcode.动态规划;

import java.util.Arrays;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class _121_买卖股票的最佳时机 {

    public int maxProfitWithDp(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] temp = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            temp[i - 1] = prices[i] - prices[i];
        }
        System.out.println(Arrays.toString(temp));
        int dp = temp[0];
        int maxProfit = dp;
        for (int i = 1; i < temp.length; i++) {
            if (dp > 0) {
                dp = dp + temp[i];
            } else {
                dp = temp[i];
            }
            maxProfit = Math.max(maxProfit, dp);
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;

        //买入股票的最低价格
        int minPrice = prices[0];
        //卖出获取的最大利润
        int maxProfit = 0;
        //扫描
        for (int i = 1; i < length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }
}
