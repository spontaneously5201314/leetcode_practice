package com.base.structure.tree;

import com.base.structure.tree.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author 洪飞
 * @date 2020/6/15
 */
public class RBTreeTest {

    public static void main(String[] args) {
        testRBTree();
    }

    private static void testRBTree() {
        Integer[] data = new Integer[]{61, 59, 13, 86, 74, 19, 57, 44, 80, 98, 35, 93};
        RBTree<Integer> rbTree = new RBTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
    }
}