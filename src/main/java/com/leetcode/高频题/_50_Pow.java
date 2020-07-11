package com.leetcode.高频题;

public class _50_Pow {

    public static void main(String[] args) {
        _50_Pow v = new _50_Pow();
        System.out.println(v.myPow(2, 3));
        System.out.println(v.myPow(2, -3));
        System.out.println(v.powMod(123, 456, 789));
    }

    /**
     * 使用快速幂的方法，最优解
     */
    public double myPow(double x, int n) {
        boolean neg = n < 0;
        long y = neg ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                //如果最后一个二进制位是1，就累乘上x
                res *= x;
            }
            x *= x;
            //舍弃掉最后一个二进制位
            y >>= 1;
        }
        return neg ? 1 / res : res;
    }

    /**
     * 扩展：求x的y次幂之后，再对z取模
     * 需要知道一个数学计数公式：(a * b) % p = ((a % p) * (b % p)) % p
     */
    public int powMod(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                //如果最后一个二进制位是1，就累乘上x
                res = (res * x) % z;
            }
            x = (x * x) % z;
            //舍弃掉最后一个二进制位
            y >>= 1;
        }
        return res % z;
    }

    /**
     * 扩展：求x的y次幂之后，再对z取模
     * 使用递归方式
     * 需要知道一个数学计数公式：(a * b) % p = ((a % p) * (b % p)) % p
     */
    public int powMod2(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        if (y == 0) return 1 % z;
        int half = powMod2(x, y >> 1, z);
        half *= half;
        if ((y & 1) == 0) {
            return half % z;
        } else {
            return (half * (x % z)) % z;
        }
    }

    public double myPow1(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        //是否为奇数
        boolean odd = (n & 1) == 1;
        double half = myPow1(x, n >> 1);
        half *= half;
//        x = (n < 0) ? (1 / x) : x;
        return odd ? (half * x) : half;
    }
}
