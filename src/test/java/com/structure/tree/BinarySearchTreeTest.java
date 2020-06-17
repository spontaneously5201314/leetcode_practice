package com.structure.tree;

import com.structure.iter.Visitor;
import com.structure.tree.printer.BinaryTrees;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;

/**
 * @author 洪飞
 * @date 2020/6/8
 */
public class BinarySearchTreeTest {

    @Test
    public void add() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }
        BinaryTrees.println(tree);
    }

    @Test
    public void preorder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }
        BinaryTrees.println(tree);
        tree.preOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        }, true);
    }

    @Test
    public void inorder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }
        BinaryTrees.println(tree);
        tree.inOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        }, true);
    }

    @Test
    public void postorder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }
        BinaryTrees.println(tree);
        tree.postOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        }, true);
    }

    @Test
    public void levelorder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(20));
        }
        BinaryTrees.println(tree);
        tree.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        }, true);
    }
}