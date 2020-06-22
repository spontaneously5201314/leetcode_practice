package com.leetcode;

/**
 * 刷题的时候使用的数组工具类
 */
public class ArrayUtils {

    public static void print(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append("_");
            }
        }
        System.out.println(sb.toString());
    }
}
