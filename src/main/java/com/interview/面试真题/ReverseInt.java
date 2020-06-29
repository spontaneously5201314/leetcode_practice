package com.interview.面试真题;

/**
 * OKCoin算法二：反转一个整数
 * 把一个整数的各个位反转过来，比如123变成321，13579变成97531，不可以用String, StringBuilder, StringBuffer为中介
 */
public class ReverseInt {

    public static void main(String[] args) {
        ReverseInt anInt = new ReverseInt();
        System.out.println(anInt.reverse(123));
    }

    public int reversePlus(int x) {
        int ans = 0;
        while (x != 0) {
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
