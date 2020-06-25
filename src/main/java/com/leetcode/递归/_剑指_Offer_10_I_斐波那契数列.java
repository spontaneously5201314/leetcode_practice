package com.leetcode.递归;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：5
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 100
 * 注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 */
public class _剑指_Offer_10_I_斐波那契数列 {


    /**
     * 我们来总结一下斐波那契数列第 nn 项的求解方法：
     *
     * 1、n 比较小的时候，可以直接使用过递归法求解，不做任何记忆化操作，时间复杂度是 O(2^n)，存在很多冗余计算。
     * 2、一般情况下，我们使用「记忆化搜索」或者「迭代」的方法，实现这个转移方程，时间复杂度和空间复杂度都可以做到 O(n)。
     * 3、为了优化空间复杂度，我们可以不用保存 f(x - 2)之前的项，我们只用三个变量来维护 f(x)、f(x - 1)和 f(x - 2)，你可以理解成是把「滚动数组思想」应用在了动态规划中，也可以理解成是一种递推，这样把空间复杂度优化到了 O(1)。
     * 4、随着 nn 的不断增大 O(n) 可能已经不能满足我们的需要了，我们可以用「矩阵快速幂」的方法把算法加速到 O(\log n)。
     * 5、我们也可以把 nn 代入斐波那契数列的通项公式计算结果，但是如果我们用浮点数计算来实现，可能会产生精度误差。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     */


    /**
     * 第一种解法：使用递归来解决，但是时间复杂度是O(n^2)，太慢
     *
     * @param n
     * @return
     */
//    public int fib(int n) {
//        if (n == 1 || n == 2) return 1;
//        return fib(n - 1) + fib(n - 2);
//    }

    /**
     * 上面是正常的递归，现在这个是尾递归
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        return fib(n, 1, 1);
    }

    private int fib(int n, int first, int second) {
        if (n <= 1) return first;
        return fib(n - 1, second, first + second);
    }

    /**
     * 第二种解法：使用数组来优化，将第一种方法中的重复计算给缓存到数组中
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[2] = array[1] = 1;
        int fibRecur = fibRecur(n, array);
        if (fibRecur > 1000000007) {
            fibRecur %= 1000000007;
        }
        return fibRecur;
    }

    private int fibRecur(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fibRecur(n - 1, array) + fibRecur(n - 2, array);
        }
        return array[n];
    }

    /**
     * 第三种解法：使用动态数组来解决，因为在使用过程中只使用到了两个元素，没必要用一整个数组
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
//            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
            //模2的操作，都可以替换成i & 1，因为模2其实看的就是数字的最后一位是0还是1，所以上面这句可以替换成下面这句
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }

    /**
     * 第四种解法：直接使用两个数字进行运算
     *
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n <= 2) return 1;
        int first = 1, second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

    /**
     * 第五种解法：使用线性代数的特征方程
     *
     * @param n
     * @return
     */
    public int fib4(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }
}
