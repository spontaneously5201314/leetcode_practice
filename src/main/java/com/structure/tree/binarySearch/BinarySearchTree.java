package com.structure.tree.binarySearch;

import com.structure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    private ThreadLocal<Boolean> visitStop = new ThreadLocal<>();

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    //--------------------------------CRUD等方法--------------------------------

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        do {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.element = element;
                return;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

        //度为2的节点
        if (node.hasTwoChildren()) {
            //找到前驱或者后继节点都可以，这里以后继节点为例
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        //删除node节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为1的节点
            replacement.parent = node.parent;
            if (node.parent == null) {
                //删除的度为1的节点是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

        } else if (node.parent == null) {
            //node是叶子节点，并且是根节点，表示这个数只有root这一个节点
            root = null;
        } else {
            //node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        Node<E> predecessor = predecessor(node);
        if (predecessor != null) {
            node.element = predecessor.element;
            if (predecessor.left != null) {
                predecessor.parent.left = predecessor.right;
                predecessor.left.parent = predecessor.parent;
//                predecessor.left.parent = predecessor.parent;
//                predecessor = predecessor.left;
                predecessor.left = null;
            }
        } else {
            Node<E> successor = successor();
            if (successor != null) {
                node.element = successor.element;
                if (successor.right != null) {
                    successor.parent.right = successor.left;
                    successor.right.parent = successor.parent;
//                    successor.right.parent = successor.parent;
//                    successor = successor.right;
                    successor.right = null;
                }
            }
        }

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        if (visitStop.get() != null && visitStop.get()) return;
        Boolean stop = visitor.visit(node.element);
        visitStop.set(stop);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }

    /**
     * 层次遍历
     */
    public void levelorderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //--------------------------------找前驱/后继节点--------------------------------

    public Node<E> predecessor() {
        return predecessor(root);
    }

    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        //前驱节点在左子树的最右边
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> successor() {
        return successor(root);
    }

    private Node<E> successor(Node<E> node) {
        //后继结点在右子树的最左边
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        //从父节点、祖父节点中寻找后继结点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    //--------------------------------用层次遍历判断是不是完全二叉树--------------------------------

    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.right != null && node.left == null) {
                return false;
            } else {
                isLeaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return false;
    }

    //--------------------------------用层次遍历获取树的高度--------------------------------

    public int height() {
        if (root == null) return 0;

        int height = 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    //--------------------------------对外提供访问接口--------------------------------

    public interface Visitor<E> {
        /**
         * 访问二叉树，并返回下一次是否可以访问
         *
         * @param element 返回的元素
         * @return true表示下一次不能访问，false表示下一次可以访问
         */
        Boolean visit(E element);
    }

    //--------------------------------私有方法--------------------------------

    /**
     * 判断元素是否为空
     *
     * @param element 要判断的元素
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     *
     * @param e1 需要对比的第一个元素
     * @param e2 需要对比的第二个元素
     * @return 返回对比的结果
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    //--------------------------------构造树--------------------------------

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

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

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    //--------------------------------打印树--------------------------------

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
        return ((Node) node).element;
    }
}
