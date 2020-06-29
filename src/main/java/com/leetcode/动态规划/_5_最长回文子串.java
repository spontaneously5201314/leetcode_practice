package com.leetcode.动态规划;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class _5_最长回文子串 {

    public static void main(String[] args) {
        _5_最长回文子串 v = new _5_最长回文子串();
        System.out.println(v.longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        if (s == null) return "";
        int length = s.length();
        if (length == 0) return "";
        if (length == 1 || (length == 2 && s.charAt(0) == s.charAt(1))) return s;

        // TODO: 2020/6/27 还没理解动态规划的精髓
        return "";
    }
}
