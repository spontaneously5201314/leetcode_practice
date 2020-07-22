package com.nowcoder.美团;

import java.util.Arrays;

/**
 * 有一个二维数组(n*n),写程序实现从右上角到左下角沿主对角线方向打印。
 * <p>
 * 给定一个二位数组arr及题目中的参数n，请返回结果数组。
 * <p>
 * 测试样例：
 * [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]],4
 * 返回：[4,3,8,2,7,12,1,6,11,16,5,10,15,9,14,13]
 */
public class _二维数组打印 {

    public static void main(String[] args) {
        _二维数组打印 v = new _二维数组打印();
        int[][] arr = new int[4][4];
        arr[0] = new int[]{1, 2, 3, 4};
        arr[1] = new int[]{5, 6, 7, 8};
        arr[2] = new int[]{9, 10, 11, 12};
        arr[3] = new int[]{13, 14, 15, 16};
        System.out.println(Arrays.toString(v.arrayPrint(arr, 4)));
    }

    /**
     * 沿着主对角线打印，所以每次打印之后x与y都要加1，直到x或y超出边界。
     * 每轮遍历的起始点，是遵循(0,n-1)...（0,0）...（n-1,0）。
     * 也就是y先减小到0，然后x增加到n-1。所以遍历的终止条件是startX>=n。
     **/
    public int[] arrayPrint(int[][] arr, int n) {
        int[] res = new int[n * n];
        int index = 0;
        int startX = 0;
        int startY = n - 1;
        while (startX < n) {
            int x = startX;
            int y = startY;
            while (x < n && y < n)
                res[index++] = arr[x++][y++];
            if (startY > 0)
                startY--;
            else
                startX++;
        }
        return res;
    }
}
