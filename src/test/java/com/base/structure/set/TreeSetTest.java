package com.base.structure.set;

import com.base.structure.iter.Visitor;

import java.util.Comparator;

/**
 * @author 洪飞
 * @date 2020/6/16
 */
public class TreeSetTest {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        set.add(11);
        set.add(22);
        set.add(33);
        System.out.println(set.size());
        System.out.println(set.isEmpty());
        System.out.println(set.contains(22));
        System.out.println(set.contains(44));
        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

}