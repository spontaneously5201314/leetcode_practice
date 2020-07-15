package com.interview.模拟面试题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum)
 */
public class _模拟面试_两数之和 {

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(sum - array[i], array[i]);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(sum - array[i])) {
                list.add(array[i]);
                list.add(sum - array[i]);
                break;
            }
        }
        return list;
    }
}
