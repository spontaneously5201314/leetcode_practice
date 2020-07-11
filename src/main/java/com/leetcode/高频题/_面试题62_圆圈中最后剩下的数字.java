package com.leetcode.高频题;

public class _面试题62_圆圈中最后剩下的数字 {

    public static void main(String[] args) {
        _面试题62_圆圈中最后剩下的数字 v = new _面试题62_圆圈中最后剩下的数字();
        System.out.println(v.lastRemaining(5, 3));
    }

    /**
     * 可以使用环形链表来解决，只是需要自己手写一个环形链表
     * 还可以利用数学公式：f(n, m) = (f(n-1, m) + m) % n
     */
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        return (lastRemaining(n - 1, m) + m) % n;
    }

    /**
     * 上面是递归方式，这次采用迭代方式
     */
    public int lastRemainingIter(int n, int m) {
        if (n == 1) return 0;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res = (res + m) % i;
        }
        return res;
    }
}
