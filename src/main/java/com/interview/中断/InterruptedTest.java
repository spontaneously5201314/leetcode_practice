package com.interview.中断;

import com.google.common.util.concurrent.Runnables;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 洪飞
 * @date 2020/6/4
 */
public class InterruptedTest {

    public static void main(String[] args) {
        PrimeThread thread = new PrimeThread();
        thread.start();

        System.out.println("========");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private static class PrimeThread extends Thread {

        @Override
        public void run() {
            AtomicLong num = new AtomicLong(0L);
            while (true) {
                long number = num.get();
                if (number % 2 == 0) {
                    System.out.println("can % 2");
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupt");
                    return;
                }
                num.addAndGet(1);
            }
        }
    }
}
