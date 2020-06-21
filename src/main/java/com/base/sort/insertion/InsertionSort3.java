package com.base.sort.insertion;

import com.base.sort.BinarySearch;
import com.base.sort.Sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            insert(i, search(i));
        }
    }

    /**
     * 将数组原来位置的数据，插入到目的下标位置
     *
     * @param source 原来位置下标
     * @param dest   目标位置下标
     */
    private void insert(int source, int dest) {
        T temp = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = temp;
    }

    /**
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序数组的区间范围是 [0, index)
     * 见{@link BinarySearch#search(int[], int)}
     *
     * @param index
     * @return
     */
    private int search(int index) {
        if (array == null || array.length == 0) return 0;

        int begin = 0;
        int end = index;

        while (begin < end) {
            int middle = (begin + end) >> 1;
            if (array[middle].compareTo(array[index]) < 0) begin = middle + 1;
            else end = middle;
        }
        return begin;
    }
}
