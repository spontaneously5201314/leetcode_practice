package com.structure.dynamicArray;

/**
 * 传统数组无法满足动态添加和删除数组中的数据，故手写一个动态数组，对标{@link java.util.ArrayList}
 *
 * @author 洪飞
 * @date 2020/5/28
 */
public class ArrayList<E> {

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return 0;
    }

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty() {
        return false;
    }

    /**
     * 是否包含某个元素
     *
     * @param element 判断的元素
     * @return
     */
    boolean contains(E element) {
        return false;
    }

    /**
     * 添加元素到最后面
     *
     * @param element 待添加的元素
     */
    void add(E element) {
    }

    /**
     * 返回index位置对应的元素
     *
     * @param index 位置下标
     * @return 该位置对应的元素
     */
    E get(int index) {
        return null;
    }

    /**
     * 设置index位置的元素
     *
     * @param index   index下标
     * @param element 要更换成的元素
     * @return 返回更换之前的元素
     */
    E set(int index, E element) {
        return null;
    }

    /**
     * 往index位置添加元素
     *
     * @param index   index下标
     * @param element 需要添加的元素
     */
    void add(int index, E element) {

    }

    /**
     * 删除index位置对应的元素
     *
     * @param index index下标
     * @return 返回删除的元素
     */
    E remove(int index) {
        return null;
    }

    /**
     * 查看元素的位置
     *
     * @param element 待查看的元素
     * @return 返回该元素对应的下标
     */
    int indexOf(E element) {
        return 0;
    }

    /**
     * 清楚所有元素
     */
    void clear() {

    }
}
