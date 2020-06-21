package com.base.sort;

import com.base.sort.bubble.BubbleSort1;
import com.base.sort.bubble.BubbleSort2;
import com.base.sort.bubble.BubbleSort3;
import com.base.sort.heap.HeapSort;
import com.base.sort.insertion.InsertionSort1;
import com.base.sort.insertion.InsertionSort2;
import com.base.sort.insertion.InsertionSort3;
import com.base.sort.merge.MergeSort;
import com.base.sort.quick.QuickSort;
import com.base.sort.selection.SelectionSort;
import com.base.sort.shell.ShellSort;
import com.base.util.Asserts;
import com.base.util.RandomArrayUtils;

import java.util.Arrays;

/**
 * 主要用来对比各种算法的执行效果
 */
public class SortCompare {

    public static void main(String[] args) {
        Integer[] array = RandomArrayUtils.random(10000, 1, 5000000);

        testSorts(array,
//                new BubbleSort1(),
//                new BubbleSort2(),
//                new BubbleSort3(),
//                new SelectionSort(),
//                new HeapSort(),
//                new InsertionSort1(),
//                new InsertionSort2(),
//                new InsertionSort3(),
//                new MergeSort(),
//                new QuickSort(),
                new ShellSort()
        );
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = RandomArrayUtils.copy(array);
            sort.sort(newArray);
            Asserts.test(RandomArrayUtils.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
