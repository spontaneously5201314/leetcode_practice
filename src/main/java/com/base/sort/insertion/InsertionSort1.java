package com.base.sort.insertion;

import com.base.sort.Sort;

/**
 * 插入排序
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = 1; end < array.length; end++) {
            for (int begin = array.length - 1; begin > 0; begin--) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }

        //另外一种使用for+while的写法
//        for (int begin = 1; begin < array.length; begin++) {
//            int cur = begin;
//            while (cur > 0 && cmp(cur, cur - 1) < 0) {
//                swap(cur, cur - 1);
//                cur--;
//            }
//        }
    }
}
