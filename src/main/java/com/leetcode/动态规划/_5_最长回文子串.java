package com.leetcode.动态规划;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

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
        System.out.println(v.longestPalindromeDp("babad"));
    }

    /**
     * 中心扩展解法
     */
    public String longestPalindromeMid(String s) {
        if (s == null) return null;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return s;

        int begin = 0;
        int maxLen = 1;

        for (int i = chars.length - 2; i >= 1; i--) {
            int len1 = palindromeLength(chars, i - 1, i + 1);
            int len2 = palindromeLength(chars, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - (maxLen - 1) >> 1;
            }
        }
        if (chars[0] == chars[1] && maxLen < 2) {
            begin = 0;
            maxLen = 2;
        }

        return new String(chars, begin, maxLen);
    }

    private int palindromeLength(char[] cs, int begin, int end) {
        while (begin > 0 && end < cs.length && cs[begin] == cs[end]) {
            begin--;
            end++;
        }
        return end - begin - 1;
    }

    /**
     * 动态规划解法
     */
    public String longestPalindromeDp(String s) {
        if (s == null) return null;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return s;

        int begin = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i; j < chars.length; j++) {
                int len = j - i + 1;
                dp[i][j] = (chars[i] == chars[j]) && (len <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) {
                    begin = i;
                    maxLen = len;
                }
            }
        }
        return new String(chars, begin, maxLen);
    }
}
