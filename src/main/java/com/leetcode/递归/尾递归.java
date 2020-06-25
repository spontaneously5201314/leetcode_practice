package com.leetcode.递归;

/**
 * 如果是需要些递归来解决问题，尽量转换成尾递归，因为尾递归在栈的使用上有优化
 */
public class 尾递归 {

    /**
     * 该方法用于求n的阶乘，但是因为不是尾递归，所以空间复杂度是O（n），我们想要将其转换成尾递归
     *
     * @param n
     * @return
     */
//    public int facttorial(int n) {
//        if (n <= 1) return n;
//        return n * facttorial(n - 1);
//    }

    /**
     * 这种方式就是尾递归的方式，但是尾递归的方式的确很难想出来
     *
     * @param n
     * @return
     */
    public int facttorial(int n) {
        return facttorial(n, 1);
    }

    public int facttorial(int n, int result) {
        if (n <= 1) return result;
        return facttorial(n - 1, n * result);
    }
}
