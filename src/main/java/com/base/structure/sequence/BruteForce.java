package com.base.structure.sequence;

/**
 * 字符串匹配算法--暴力解法
 */
public class BruteForce {

    public static int indexOfV3(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        int tlen = text.length();
        if (tlen == 0) return -1;
        int plen = pattern.length();
        if (plen == 0) return -1;
        if (plen > tlen) return -1;

        char[] tchars = text.toCharArray();
        char[] pchars = pattern.toCharArray();

        for (int t = 0; t < tlen - plen; t++) {
            for (int p = 0; p < plen; p++) {
                if (pchars[p] != tchars[t + p]) break;
                if (p == plen - 1) return t;
            }
        }
        return -1;
    }

    public static int indexOfV2(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        int tlen = text.length();
        if (tlen == 0) return -1;
        int plen = pattern.length();
        if (plen == 0) return -1;
        if (plen > tlen) return -1;

        char[] tchars = text.toCharArray();
        char[] pchars = pattern.toCharArray();

        int t = 0, p = 0, lenDelta = tlen - plen;
        while (p < plen && t - p <= lenDelta) {
            if (tchars[t] == pchars[p]) {
                t++;
                p++;
            } else {
                t -= p - 1;
                p = 0;
            }
        }
        return p == plen ? t - p : -1;
    }

    public static int indexOfV1(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        int tlen = text.length();
        if (tlen == 0) return -1;
        int plen = pattern.length();
        if (plen == 0) return -1;
        if (plen > tlen) return -1;

        char[] tchars = text.toCharArray();
        char[] pchars = pattern.toCharArray();

        int t = 0, p = 0;
        while (t < tlen && p < plen) {
            if (tchars[t] == pchars[p]) {
                t++;
                p++;
            } else {
                t -= p - 1;
                p = 0;
            }
        }
        return p == plen ? t - p : -1;
    }
}
