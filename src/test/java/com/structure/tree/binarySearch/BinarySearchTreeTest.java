package com.structure.tree.binarySearch;

import com.structure.tree.printer.BinaryTrees;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BinarySearchTreeTest {

    BinarySearchTree<Integer> bst = null;

    @Before
    public void setUp() throws Exception {
        if (bst == null) {
            bst = new BinarySearchTree<>();
            for (int i = 0; i < 30; i++) {
                int randomInt = new Random().nextInt(100);
                bst.add(randomInt);
            }
        }
    }

    @Test
    public void add() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 1
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.println(tree);
    }

    @Test
    public void preorderTraversal() {
        BinaryTrees.println(bst);
        bst.preorderTraversal(element -> element > 10);
    }

    @Test
    public void inorderTraversal() {
    }

    @Test
    public void postorderTraversal() {
    }

    @Test
    public void levelorderTraversal() {
    }

    @Test
    public void height() {
        BinaryTrees.println(bst);
        System.out.println(bst.height());
    }

    @Test
    public void isComplete() {
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    @Test
    public void predecessor() {
        BinaryTrees.println(bst);
        System.out.println(bst.predecessor());
    }

    @Test
    public void successor() {
        BinaryTrees.println(bst);
        System.out.println(bst.successor());
    }

    @Test
    public void remove() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 1, 3, 6, 8, 7, 9, 6, 4, 1, 2, 5, 98
        };
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
        BinaryTrees.println(tree);
        tree.remove(7);
        BinaryTrees.println(tree);
    }
}