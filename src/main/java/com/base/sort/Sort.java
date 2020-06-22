package com.base.sort;

import com.base.sort.counting.CountingSort;
import com.base.sort.selection.SelectionSort;
import com.base.sort.shell.ShellSort;

import java.text.DecimalFormat;

/**
 * 抽取比较的父类
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class Sort<T extends Comparable<T>> implements Comparable<T> {

    protected T[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        this.array = array;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }


    @Override
    public int compareTo(T o) {
        Sort sort = (Sort) o;
        int result = (int) (time - sort.time);
        if (result != 0) return result;

        result = cmpCount - sort.cmpCount;
        if (result != 0) return result;

        return swapCount - sort.swapCount;
    }

    protected abstract void sort();

    /**
     * 返回值等于0，代表 array[i1] == array[i2]
     * 返回值小于0，代表 array[i1] < array[i2]
     * 返回值大于0，代表 array[i1] > array[i2]
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    protected int cmp(T v1, T v2) {
        cmpCount++;
        return v1.compareTo(v2);
    }

    protected void swap(int i1, int i2) {
        swapCount++;
        T tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    private boolean isStable() {
//        if (this instanceof RadixSort) return true;
        if (this instanceof CountingSort) return true;
        if (this instanceof ShellSort) return false;
        if (this instanceof SelectionSort) return false;
        StableCompare[] stableCompares = new StableCompare[20];
        for (int i = 0; i < stableCompares.length; i++) {
            stableCompares[i] = new StableCompare(i * 10, 10);
        }
        sort((T[]) stableCompares);
        for (int i = 1; i < stableCompares.length; i++) {
            int score = stableCompares[i].score;
            int prevScore = stableCompares[i - 1].score;
            if (score != prevScore + 10) return false;
        }
        return true;
    }
}
