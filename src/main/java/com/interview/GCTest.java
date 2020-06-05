package com.interview;

import java.util.List;

/**
 * @author 洪飞
 * @date 2020/6/2
 */
public class GCTest {

    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        List<GCTest> tests = new java.util.ArrayList<>();
        while (true) {
            tests.add(new GCTest());
            Thread.sleep(10);
        }
    }
}
