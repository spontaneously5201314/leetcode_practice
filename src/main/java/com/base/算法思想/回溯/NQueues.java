package com.base.算法思想.回溯;

/**
 * 通过N皇后问题来学习回溯的算法思想
 * 因为N皇后是N*N棋盘，按照行来摆放，从0开始，每行选择一个位置，然后接着摆放下一行，如果出现一行没有地方摆放，那就回到上一行，换位置，继续摆放下一行
 * 所以，会使用到递归、剪枝以及回溯来解决N皇后的问题
 */
public class NQueues {

    public static void main(String[] args) {
        NQueues nQueues = new NQueues();
        nQueues.placeQueues(8);
    }

    /**
     * 表示每行皇后的列号，因为是N*N的棋盘，数组的下标表示行号，那么数组下标对应的值就表示该行皇后摆放的列号
     * 比如说cols[0]=0，表示第0行的皇后放在第一列
     */
    int[] cols;

    /**
     * 表示该N皇后一共有多少种不同的摆法
     */
    int ways;

    /**
     * 摆放N皇后
     *
     * @param n 需要摆放的皇后的数量
     */
    public void placeQueues(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后共有" + ways + "不同的摆法");
    }

    /**
     * 摆放第row行的皇后位置
     *
     * @param row 摆放的行号
     */
    private void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    /**
     * 判断第row行第col列是否可以摆放皇后
     *
     * @param row 行号
     * @param col 列号
     * @return true表示可以摆放，false表示不可以摆放
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col列已经有皇后
            if (cols[i] == col) {
                System.out.println("[" + row + "][" + col + "]=false");
                return false;
            }
            // 第i行的皇后跟第row行第col列格子处在同一斜线上
            if (row - i == Math.abs(col - cols[i])) {
                System.out.println("[" + row + "][" + col + "]=false");
                return false;
            }
        }
        System.out.println("[" + row + "][" + col + "]=true");
        return true;
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }
}
