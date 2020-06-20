package com.base.structure.set;

import com.base.structure.iter.Visitor;
import com.base.structure.tree.RBTree;

import java.util.Comparator;

/**
 * 使用红黑树来实现Set，对标{@link java.util.TreeSet}
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public class TreeSet<E> implements Set<E> {

    private RBTree<E> tree;

    public TreeSet() {
        this(null);
    }

    public TreeSet(Comparator<E> comparator) {
        tree = new RBTree<>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inOrder(new Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        }, false);
    }
}
