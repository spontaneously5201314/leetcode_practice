package com.leetcode.字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * 求无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author 洪飞
 * @date 2020/5/25
 */
public class _3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLength = 0;
        int now = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                now = Math.max(map.get(s.charAt(i)) + 1, now);
                if ((i - now + 1) > maxLength) {
                    maxLength = i - now + 1;
                }
            } else {
                if ((i - now + 1) > maxLength) {
                    maxLength = i - now + 1;
                }
            }
            map.put(s.charAt(i), i);
        }
        return maxLength;
    }
}
