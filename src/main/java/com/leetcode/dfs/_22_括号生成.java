package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class _22_括号生成 {

    public static void main(String[] args) {
        _22_括号生成 v = new _22_括号生成();
        System.out.println(v.generateParenthesis(3));
    }


    /**
     * 思路：当左括号和右括号数量一样时，只能选择左括号
     * 什么情况可以选择左括号？ 左括号的数量 > 0
     * 什么情况可以选择右括号？ （右括号的数量 > 0）&& （右括号的数量 != 左括号的数量）
     */

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) return list;
//        if (n == 0) {
//            list.add("");
//            return list;
//        }

        dfs(0, n, n, new char[n << 1], list);

        return list;
    }

    /**
     * DFS
     *
     * @param index       层号
     * @param leftRemain  左括号剩余的个数
     * @param rightRemain 右括号剩余的个数
     * @param chars       存放每一层的选择
     * @param list        最终存放结果的list
     */
    private void dfs(int index, int leftRemain, int rightRemain, char[] chars, List<String> list) {
        if (index == chars.length) {
            list.add(new String(chars));
            return;
        }

        //什么情况可以选择左括号？ 左括号的数量 > 0
        if (leftRemain > 0) {
            chars[index] = '(';
            dfs(index + 1, leftRemain - 1, rightRemain, chars, list);
        }
        //什么情况可以选择右括号？ （右括号的数量 > 0）&& （右括号的数量 != 左括号的数量）
        if (rightRemain > 0 && leftRemain != rightRemain) {
            chars[index] = ')';
            dfs(index + 1, leftRemain, rightRemain - 1, chars, list);
        }
    }

}
