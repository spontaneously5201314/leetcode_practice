package com.leetcode.字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 * <p>
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 */
public class _345_反转字符串中的元音字母 {

    public static void main(String[] args) {
        _345_反转字符串中的元音字母 v = new _345_反转字符串中的元音字母();
//        System.out.println(v.reverseVowels("leetcode"));
        System.out.println(v.reverseVowels(" "));
    }


    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        int begin = 0;
        int end = chars.length - 1;
        while (begin <= end) {
            if (!isVowel(chars[begin])) begin++;
            if (!isVowel(chars[end])) end--;
            if (begin > end) break;
            if (isVowel(chars[begin]) && isVowel(chars[end])) {
                swap(chars, begin, end);
                begin++;
                end--;
            }
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int begin, int end) {
        char temp = chars[begin];
        chars[begin] = chars[end];
        chars[end] = temp;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
