package com.leetcode.字符串;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class _242_有效的字母异位词 {

    public static void main(String[] args) {
        String s = "a";
        String t = "b";
        _242_有效的字母异位词 v = new _242_有效的字母异位词();
        System.out.println(v.isAnagram(s, t));
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() == 0 && t.length() == 0) return true;
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--counts[t.charAt(i) - 'a'] < 0) return false;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) return false;
        }
        return true;
    }
}
