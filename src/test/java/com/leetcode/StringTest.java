package com.leetcode;

/**
 * @author 洪飞
 * @date 2020/5/26
 */
public class StringTest {

    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = "abc";
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());
//
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));


        String s1 = "hello";
        String s2 = new StringBuilder("he").append("llo").toString();
        String s3 = s2.intern();

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}
