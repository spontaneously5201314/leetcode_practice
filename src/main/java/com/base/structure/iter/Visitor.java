package com.base.structure.iter;

/**
 * 迭代器抽象类，统一迭代操作
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public abstract class Visitor<E> {
    /**
     * 该遍历操作是否是停止
     */
    public boolean stop = false;

    /**
     * 对树中的节点进行遍历可以执行的操作
     *
     * @param element 遍历中待操作的节点的数据
     * @return 返回true就表示停止遍历，false表示可以继续遍历下一个节点
     */
    public abstract boolean visit(E element);
}
