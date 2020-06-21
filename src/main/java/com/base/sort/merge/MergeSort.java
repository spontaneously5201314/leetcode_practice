package com.base.sort.merge;

import com.base.sort.Sort;

@SuppressWarnings("unchecked")
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin, end)范围内的数据进行归并排序
     */
    private void sort(int begin, int end) {
        //表示切割后的子序列只有一个元素，就不需要再进行排序了
        if (end - begin < 2) return;

        int middle = (begin + end) >> 1;
        sort(begin, middle);
        sort(middle, end);
        merge(begin, middle, end);
    }

    /**
     * 将[begin, middle)和[middle, end)范围的序列合并成一个有序序列
     */
    private void merge(int begin, int middle, int end) {
        int li = 0, le = middle - begin;
        int ri = middle, re = end;
        int ai = begin;

        //备份坐标数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        //如果左边还没有结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
