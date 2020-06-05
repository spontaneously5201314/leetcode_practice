package com.interview.中断;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author 洪飞
 * @date 2020/6/4
 */
public class ThreadPoolInterruptTest {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadFactory build = new ThreadFactoryBuilder().setNameFormat("interrupt_%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1024), build, new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        poolExecutor.shutdownNow();
    }
}
