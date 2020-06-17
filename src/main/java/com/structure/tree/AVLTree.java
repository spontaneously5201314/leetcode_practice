package com.structure.tree;

import java.util.Comparator;

/**
 * 平衡二叉树实现
 *
 * @author 洪飞
 * @date 2020/6/15
 */
public class AVLTree<E> extends BalancedBinarySearchTree<E> {

    /**
     * 是否使用统一的平衡方式
     */
    boolean useUniformBalanced = false;

    public AVLTree() {
        this(null);
    }

    public AVLTree(boolean useUniformBalanced) {
        this(null);
        this.useUniformBalanced = useUniformBalanced;
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }


    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                rebalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                rebalance(node);
                //整棵树恢复平衡
            }
        }
    }

    /**
     * 针对给定的不平衡节点，进行再平衡操作
     *
     * @param node 待平衡的节点，即高度最低的不平衡节点
     */
    private void rebalance(Node<E> node) {
        if (useUniformBalanced) {
            rebalanceWithNoUniform(node);
        } else {
            rebalanceWithUniform(node);
        }
    }

    /**
     * 统一所有的旋转操作
     *
     * @param grand 待平衡的节点，即高度最低的不平衡节点
     */
    private void rebalanceWithUniform(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            // L
            if (node.isLeftChild()) {
                // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else {
                // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else {
            // R
            if (node.isLeftChild()) {
                // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else {
                // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        //更新树的AVL树的高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    private void rebalanceWithNoUniform(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            //L
            if (node.isLeftChild()) {
                //LL
                rotateRight(grand);
            } else {
                //LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            //R
            if (node.isLeftChild()) {
                //RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        //更新树的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 更新给定节点的高度
     *
     * @param node 给定的节点
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 判断一个节点是否是平衡的，即该节点的平衡因子的绝对值小于等于1
     *
     * @param node 给定节点
     * @return true表示该节点是平衡的，false表示该节点不是平衡的
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<E>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
