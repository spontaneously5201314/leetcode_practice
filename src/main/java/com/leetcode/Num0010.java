package com.leetcode;

/**
 * I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * 提示：
 * 0 <= n <= 100
 * 注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 *
 * @author 洪飞
 * @date 2020/5/27
 */
public class Num0010 {

    public static void main(String[] args) {
        System.out.println(fib(50));
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * 计算斐波拉切数列，不能使用递归，因为会超时，计算慢
     *
     * @param n 要计算的位数
     * @return 返回第n位的值
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
            if (second > 1000000007){
                second %= 1000000007;
            }
        }
        return second;
    }
}
