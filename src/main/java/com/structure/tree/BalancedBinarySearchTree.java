package com.structure.tree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 * 该树的出现是为了统一AVL树和RB树中统一的旋转功能
 *
 * @author 洪飞
 * @date 2020/6/15
 */
public class BalancedBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalancedBinarySearchTree() {
    }

    public BalancedBinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 公共代码，不管是左旋、右旋，都要执行的
     *
     * @param grand  失衡节点
     * @param parent 失衡节点的tallerChild
     * @param child  grand和parent需要交换的子树（本来是parent的子树，后面会变成grand的子树）
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //让parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //grand是root节点
            root = parent;
        }

        //更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        //更新grand的parent
        grand.parent = parent;
    }

    /**
     * 统一所有的旋转操作
     *
     * @param r
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     */
    protected void rotate(Node<E> r /*子树的根节点*/, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
