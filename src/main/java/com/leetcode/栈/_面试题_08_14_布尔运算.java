package com.leetcode.栈;

/**
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * <p>
 * 示例 1:
 * 输入: s = "1^0|0|1", result = 0
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 * <p>
 * 示例 2:
 * 输入: s = "0&0&0&1^1|0", result = 1
 * 输出: 10
 * <p>
 * 提示：
 * 运算符的数量不超过 19 个
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boolean-evaluation-lcci
 *
 * @author 洪飞
 * @date 2020/6/4
 */
public class _面试题_08_14_布尔运算 {

    /**
     * 0 & 1  ==> 0
     * 0 | 1  ==> 1
     * 0 ^ 1  ==> 1
     * 1 & 1  ==> 1
     * 1 | 1  ==> 1
     * 1 ^ 1  ==> 0
     * 0 & 0  ==> 0
     * 0 | 0  ==> 0
     * 0 ^ 0  ==> 0
     */

    public static void main(String[] args) {
        System.out.println(16^(~16));
    }

    public static int countEval(String s, int result) {
        return 0;
    }
}
