package com.jol;

/**
 * 用反证法证明Java是会进行指令重排序的
 * 两个线程one和other会交替执行，只会出现三种结果，（0,1）、(1,0)或者(1,1)
 * 如果出现了(0,0)，则表明出现了x=b和y=a跑到a=1和b=1前面执行了，则说明出现了重排序
 */
public class DisOrder {

    static int x, y, a, b;

    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        for (; ; ) {
            j++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            one.start();
            other.start();
            one.join();
            other.join();
            String result = "第" + j + "次 （" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {

            }
            j++;
        }
    }
}
