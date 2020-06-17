package com.structure.set;

import com.structure.iter.Visitor;

/**
 * @author 洪飞
 * @date 2020/6/16
 */
public class LinkedSetTest {

    public static void main(String[] args) {
        LinkedSet<Integer> set = new LinkedSet<>();
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