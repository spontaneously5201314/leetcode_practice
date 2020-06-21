package com.base.sort.insertion;

import com.base.sort.Sort;

/**
 * 优化，不采用每次都比较交换位置，使用每次比较最后整体挪动的方式
 *
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
//        for (int end = 1; end < array.length; end++) {
//            int startIndex = end;
//            T temp = array[startIndex];
//            for (int begin = array.length - 1; begin > 0; begin--) {
//                if (cmp(begin, begin - 1) < 0) {
//                    array[begin] = array[begin - 1];
//                }
//            }
//            array[startIndex] = temp;
//        }

        //另外一种使用for+while循环的写法
        for (int begin = 1; begin < array.length; begin++) {
            int curr = begin;
            T temp = array[curr];
            while (curr > 0 && cmp(temp, array[curr - 1]) < 0) {
                array[curr] = array[curr - 1];
                curr--;
            }
            array[curr] = temp;
        }
    }
}
