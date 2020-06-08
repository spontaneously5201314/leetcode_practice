package com.structure.tree;

import com.structure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉搜索树的Java实现
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements BinaryTreeInfo {

    protected Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);

        //添加的是根节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        //添加的是非根节点，首先要找到要插入的位置，再进行插入
        Node parent = root;
        Node node = root;
        int compare = 0;
        while (node != null) {
            compare = comparator.compare((E) node.element, element);
            parent = node;
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }
        Node newNode = new Node(element, parent);
        if (compare < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    @Override
    public String toString() {
        return "BinarySearchTree{}";
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node) node).toString();
    }
}
