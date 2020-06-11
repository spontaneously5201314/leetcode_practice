package com.structure.tree;

import lombok.Data;

/**
 * 手写数据结构中的树，此接口为根接口，定义一些基本的接口
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public interface Tree<E> {
    /**
     * 获取树中节点的数量
     *
     * @return 返回树中节点的数量
     */
    int size();

    /**
     * 判断树是否为空
     *
     * @return true表示空，false表示非空
     */
    boolean isEmpty();

    /**
     * 判断树中是否有节点的值等于给定值
     *
     * @param element 给定的值
     * @return true表示存在，false表示不存在
     */
    boolean contains(E element);

    /**
     * 在树中添加节点
     *
     * @param element 待添加的值
     */
    void add(E element);

    /**
     * 删除树中的节点
     *
     * @param element 被删除节点的值
     */
    void remove(E element);

    /**
     * 清空树
     */
    void clear();

    /**
     * 树的前序遍历
     *
     * @param visitor 对遍历出来的节点进行的操作
     * @param iter    true表示采用迭代方式进行遍历，false表示采用递归方式进行遍历
     */
    void preOrder(Visitor<E> visitor, boolean iter);

    /**
     * 树的中序遍历
     *
     * @param visitor 对遍历出来的节点进行的操作
     * @param iter    true表示采用迭代方式进行遍历，false表示采用递归方式进行遍历
     */
    void inOrder(Visitor<E> visitor, boolean iter);

    /**
     * 树的后序遍历
     *
     * @param visitor 对遍历出来的节点进行的操作
     * @param iter    true表示采用迭代方式进行遍历，false表示采用递归方式进行遍历
     */
    void postOrder(Visitor<E> visitor, boolean iter);

    /**
     * 树的层次遍历
     *
     * @param visitor 对遍历出来的节点进行的操作
     * @param iter    true表示采用迭代方式进行遍历，false表示采用递归方式进行遍历
     */
    void levelOrder(Visitor<E> visitor, boolean iter);

    /**
     * 获取树的高度
     *
     * @param iter true表示采用迭代方式获取树的高度，false表示采用递归方式获取树的高度
     * @return 返回该树的高度
     */
    int height(boolean iter);

    /**
     * 获取某个节点的前驱节点
     *
     * @param node 给定节点
     * @return 如果给定节点在树中不存在，则返回null
     */
    Node<E> predecessor(Node<E> node);

    /**
     * 获取某个节点的后继节点
     *
     * @param node 给定节点
     * @return 如果给定节点在树中不存在，则返回null
     */
    Node<E> successor(Node<E> node);

    /**
     * 树中节点的定义
     *
     * @param <E>
     */
    @Data
    class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         * 获取给定节点的兄弟节点，优先右节点
         *
         * @return 返回该节点的兄弟节点
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
//                    ", left=" + left.element +
//                    ", right=" + right.element +
//                    ", parent=" + parent.element +
                    '}';
        }
    }

    abstract class Visitor<E> {
        /**
         * 该遍历操作是否是停止
         */
        boolean stop;

        /**
         * 对树中的节点进行遍历可以执行的操作
         *
         * @param element 遍历中待操作的节点的数据
         * @return 返回true就表示停止遍历，false表示可以继续遍历下一个节点
         */
        abstract boolean visit(E element);
    }
}
