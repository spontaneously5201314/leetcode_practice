package com.base.structure.tree;

import com.base.structure.iter.Visitor;
import com.base.structure.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的Java版本实现
 *
 * @author 洪飞
 * @date 2020/6/8
 */
public abstract class BinaryTree<E> implements Tree<E>, BinaryTreeInfo {

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
        root = null;
        size = 0;
    }

    /**
     * 创建节点
     *
     * @param element 被创建节点的元素
     * @param parent  被创建节点的父节点
     * @return 返回被创建的节点
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    /**
     * 判断树是不是完全二叉树
     *
     * @return true表示是完全二叉树，false表示不是完全二叉树
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // 后面遍历的节点都必须是叶子节点
                leaf = true;
            }
        }

        return true;
    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    //------------------------------------遍历操作------------------------------------

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

    //------------------------------------节点操作------------------------------------

    @Override
    public int height(boolean iter) {
        if (iter) {
            return heightWithIter(root);
        } else {
            return heightWithRecur(root);
        }
    }

    @Override
    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        //前驱节点在左子树的最右边节点上
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从父节点、祖父节点中寻找前驱节点，这种情况是，针对没有左子树
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        //这种针对node.parent == null || node == node.parent.right
        return node.parent;
    }

    @Override
    public Node<E> successor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    //------------------------------------遍历操作------------------------------------

    /**
     * 下面的前序、中序和后序遍历的思路来自：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/mo-fang-di-gui-zhi-bian-yi-xing-by-sonp/
     * 完美模拟系统调用栈的逻辑
     */

    //------------------------------------迭代遍历操作------------------------------------

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
            if (visitor.visit(poll.element)) return;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }

        }
    }

    private int heightWithIter(Node<E> node) {
        if (node == null) return 0;

        int height = 0;
        //用来存储树的每一层的元素个数，使用层次遍历来获取树的高度
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--;

            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            if (levelSize == 0) {
                //意味着当前层已经访问完，要接着访问下一层了
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    //------------------------------------递归遍历操作------------------------------------

    /**
     * 针对某个节点进行递归的前序遍历
     *
     * @param node    给定的节点
     * @param visitor 对遍历的节点进行的操作
     */
    private void preOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preOrderWithRecur(node.left, visitor);
        preOrderWithRecur(node.right, visitor);
    }

    private void inOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        inOrderWithRecur(node.left, visitor);
        //下面这句不能省略，是为了更快的跳出迭代
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inOrderWithRecur(node.right, visitor);
    }

    private void postOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postOrderWithRecur(node.left, visitor);
        postOrderWithRecur(node.right, visitor);
        //下面这句不能省略，是为了更快的跳出迭代
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    private void levelOrderWithRecur(Node<E> node, Visitor<E> visitor) {
        throw new IllegalArgumentException("can not use recursion");
    }

    private int heightWithRecur(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(heightWithRecur(node.left), heightWithRecur(node.right));
    }

    //------------------------------------打印操作------------------------------------

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }
}
