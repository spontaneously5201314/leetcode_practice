package com.structure.tree;

import java.util.Comparator;

/**
 * 二叉搜索树的Java实现
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    protected Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
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
        Node<E> parent = root;
        Node<E> node = root;
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
        Node<E> newNode = new Node<E>(element, parent);
        if (compare < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    @Override
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) {
            //说明删除的是度为2的节点，那么需要将其前驱或者后继节点的值放到该节点，并将前驱或者后继节点删除
            //这里我们使用后继节点
            Node<E> successor = successor(node);
            //用后继节点的值覆盖度为2的节点的值
            node.element = successor.element;
            //删除后继节点，使用接下来的逻辑来删除后继节点
            node = successor;
        }

        //删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //说明要删除的后继节点是度为1的节点
            //更改parent
            replacement.parent = node.parent;
            //更改parent的left和right的指向
            if (node.parent == null) {
                //说明node是度为1的节点，并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            //node是叶子节点，并且是根节点
            root = null;
        } else {
            //node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    @Override
    public boolean contains(E element) {
        return node(element) != null;
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }
}
