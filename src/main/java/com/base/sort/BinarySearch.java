package com.base.sort;

import com.base.sort.insertion.InsertionSort3;

/**
 * 在一个有序数组中进行二分查找
 */
public class BinarySearch {

    /**
     * 通过二分查找找到一个元素在一个数组中的位置
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) return -1;

        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int middle = (begin + end) >> 1;
            if (array[middle] == v) return middle;
            if (array[middle] < v) begin = middle + 1;
            if (array[middle] > v) end = middle;
        }
        return -1;
    }

    /**
     * 通过二分查找找到一个元素在一个数组的中的待插入位置
     * 思路：就是找到比插入的元素大的第一个元素的位置，即begin=end的位置
     * 具体使用在插入排序的时候找到插入位置，见{@link InsertionSort3#search(int)}
     */
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) return 0;

        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int middle = (begin + end) >> 1;
            if (array[middle] < v) begin = middle + 1;
            if (array[middle] > v) end = middle;
        }
        return begin;
    }
}
