package com.leetcode.回溯;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 * <p>
 * 示例:
 * <p>
 * 输入：4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * <p>
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 */
public class _面试题_08_12_八皇后 {

    public static void main(String[] args) {
        _面试题_08_12_八皇后 v = new _面试题_08_12_八皇后();
        System.out.println(JSON.toJSONString(v.solveNQueens(4)));
    }

    int cols[];

    List<List<String>> list = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return Collections.emptyList();
        cols = new int[n];
        place(0);
        return list;
    }

    private void place(int row) {
        if (row == cols.length) {
            List<String> subList = generateResult();
            list.add(subList);
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    private List<String> generateResult() {
        List<String> subList = new ArrayList<>(cols.length);
        for (int i = 0; i < cols.length; i++) {
            StringBuilder builder = new StringBuilder();
            int col = cols[i];
            for (int i1 = 0; i1 < cols.length; i1++) {
                if (i1 != col) {
                    builder.append(".");
                } else {
                    builder.append("Q");
                }
            }
            subList.add(builder.toString());
        }
        return subList;
    }

}
