package com;

public class Test {

    int[] addOne(int[] num) {
        int[] result = new int[num.length + 1];
        int flag = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + 1 + flag;
            sum = sum % 10;
            result[i + 1] = sum;
            flag = sum / 10 > 0 ? 1 : 0;
        }
        return result;
    }
}
