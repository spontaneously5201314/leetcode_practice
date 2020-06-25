package com.base.算法思想.回溯;

/**
 * {@link NQueues}的优化版本，主要优化{@link NQueues#isValid(int, int)}方法，该方法每次都是一次循环，很耗时
 */
public class NQueues2 {

    public static void main(String[] args) {
        NQueues2 nQueues = new NQueues2();
        nQueues.placeQueues(8);
    }

    /**
     * 表示每行皇后的列号，因为是N*N的棋盘，数组的下标表示行号，那么数组下标对应的值就表示该行皇后摆放的列号
     * 比如说cols[0]=0，表示第0行的皇后放在第一列
     */
    int[] cols;
    /**
     * 标记着某一列是否有皇后
     */
    boolean[] colQs;
    /**
     * 标记着某一斜线上是否有皇后（左上角 -> 右下角）
     */
    boolean[] leftTop;
    /**
     * 标记着某一斜线上是否有皇后（右上角 -> 左下角）
     */
    boolean[] rightTop;

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
        colQs = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
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
            //以下三个判断是剪枝操作，主要注意的是两个index的计算
            if (colQs[col]) continue;
            int leftIndex = row - col + cols.length - 1;
            if (leftTop[leftIndex]) continue;
            int rightIndex = row + col;
            if (rightTop[rightIndex]) continue;

            cols[row] = col;
            colQs[col] = true;
            leftTop[leftIndex] = true;
            rightTop[rightIndex] = true;
            place(row + 1);

            //能走到这里，表示刚刚的点在摆放了皇后之后，后面的行不能摆放，故需要重置刚刚摆放的点
            colQs[col] = false;
            leftTop[leftIndex] = false;
            rightTop[rightIndex] = false;
        }
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
