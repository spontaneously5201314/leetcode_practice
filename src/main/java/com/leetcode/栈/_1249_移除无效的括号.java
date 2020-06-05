package com.leetcode.栈;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 * 请返回任意一个合法字符串。
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 * <p>
 * 示例 1：
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * <p>
 * 示例 2：
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * <p>
 * 示例 3：
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 * <p>
 * 示例 4：
 * 输入：s = "(a(b(c)d)"
 * 输出："a(b(c)d)"
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 可能是 '('、')' 或英文小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses
 *
 * @author 洪飞
 * @date 2020/6/4
 */
public class _1249_移除无效的括号 {

    public static void main(String[] args) {
        String s = "a)b(c)d";
        s = "lee(t(c)o)de)";
//        s = "))((";
        System.out.println(minRemoveToMakeValid(s));
    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> bucketIndex = new Stack<>();
        Boolean[] invalidIndex = new Boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if ('(' == temp) {
                invalidIndex[i] = true;
                bucketIndex.push(i);
            }
            if (')' == temp) {
                if (bucketIndex.isEmpty()) {
                    invalidIndex[i] = true;
                } else {
                    invalidIndex[bucketIndex.pop()] = false;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Boolean invalid = invalidIndex[i];
            if (invalid == null || !invalid) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
