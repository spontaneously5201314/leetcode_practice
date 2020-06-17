package com.structure.set;

import com.structure.iter.Visitor;

/**
 * 手写集合，对标{@link java.util.Set}
 * 集合的要点是不存在重复的元素。
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public interface Set<E> {
    /**
     * 获取集合大小
     *
     * @return 返回集合大小
     */
    int size();

    /**
     * 判断集合是否为空
     *
     * @return true表示是空，false表示非空
     */
    boolean isEmpty();

    /**
     * 清空集合
     */
    void clear();

    /**
     * 判断集合中是否包含某个元素
     *
     * @param element 待定位的元素
     * @return true表示集合中包含该元素，false表示不包含
     */
    boolean contains(E element);

    /**
     * 往集合中添加元素
     *
     * @param element 待添加的元素
     */
    void add(E element);

    /**
     * 从集合中移除元素
     *
     * @param element 待移除的元素
     */
    void remove(E element);

    /**
     * 遍历集合
     *
     * @param visitor 遍历集合元素之后做的操作
     */
    void traversal(Visitor<E> visitor);
}
