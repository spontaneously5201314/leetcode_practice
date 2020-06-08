package com.structure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的Java版本实现
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public abstract class BinaryTree<E> implements Tree<E> {

    protected int size = 0;

    protected Node<E> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {

    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    @Override
    public void preOrder(Visitor<E> visitor, boolean iter) {
        if (visitor == null) return;
        if (iter) {
            preOrderWithIter(root, visitor);
        } else {
            preOrderWithRecur(root, visitor);
        }
    }

    @Override
    public void inOrder(Visitor<E> visitor, boolean iter) {
        if (visitor == null) return;

        if (iter) {
            inOrderWithIter(root, visitor);
        } else {
            inOrderWithRecur(root, visitor);
        }
    }

    @Override
    public void postOrder(Visitor<E> visitor, boolean iter) {
        if (visitor == null) return;

        if (iter) {
            postOrderWithIter(root, visitor);
        } else {
            postOrderWithRecur(root, visitor);
        }
    }

    @Override
    public void levelOrder(Visitor<E> visitor, boolean iter) {
        if (visitor == null) return;

        if (iter) {
            levelOrderWithIter(root, visitor);
        } else {
            levelOrderWithRecur(root, visitor);
        }
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public Node<E> predecessor(Node<E> node) {
        return null;
    }

    @Override
    public Node<E> successor(Node<E> node) {
        return null;
    }

    //------------------------------------遍历操作------------------------------------

    /**
     * 下面的前序、中序和后序遍历的思路来自：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/mo-fang-di-gui-zhi-bian-yi-xing-by-sonp/
     * 完美模拟系统调用栈的逻辑
     */

    /**
     * 针对某个节点进行迭代的前序遍历
     *
     * @param root    给定的节点
     * @param visitor 对遍历的节点进行的操作
     */
    private void preOrderWithIter(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            if (pop != null) {
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                stack.push(pop);
                stack.push(null);
            } else {
                visitor.visit(stack.pop().element);
            }
        }
    }

    private void inOrderWithIter(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            if (pop != null) {
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                stack.push(pop);
                stack.push(null);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            } else {
                visitor.visit(stack.pop().element);
            }
        }
    }

    private void postOrderWithIter(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            if (pop != null) {
                stack.push(pop);
                stack.push(null);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            } else {
                visitor.visit(stack.pop().element);
            }
        }
    }

    private void levelOrderWithIter(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            visitor.visit(poll.element);
        }
    }

    /**
     * 针对某个节点进行递归的前序遍历
     *
     * @param node    给定的节点
     * @param visitor 对遍历的节点进行的操作
     */
    private void preOrderWithRecur(Node<E> node, Visitor visitor) {
        if (node == null) return;

        visitor.visit(node.element);
        preOrderWithRecur(node.left, visitor);
        preOrderWithRecur(node.right, visitor);
    }

    private void inOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        inOrderWithRecur(node.left, visitor);
        visitor.visit(node.element);
        inOrderWithRecur(node.right, visitor);
    }

    private void postOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        postOrderWithRecur(node.left, visitor);
        postOrderWithRecur(node.right, visitor);
        visitor.visit(node.element);
    }

    private void levelOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        throw new IllegalArgumentException("can not use recursion");
    }
}
