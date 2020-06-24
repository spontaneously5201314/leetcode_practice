package com.base.structure.graph.weight;

/**
 * 图中权重的管理
 *
 * @param <E>
 */
public interface WeightManager<E> {
    /**
     * 比较两个权重
     *
     * @param w1 待比较的第一个权重
     * @param w2 待比较的第二个权重
     * @return true表示第一个权重大于第二个权重，false表示第一个权重小于第二个权重
     */
    int compare(E w1, E w2);

    /**
     * 两个权重相加
     *
     * @param w1 待相加第一个权重
     * @param w2 待相加第二个权重
     * @return 返回相加后的权重结果
     */
    E add(E w1, E w2);

    /**
     * 将权重置为0
     *
     * @return 返回重置后的权重
     */
    E zero();
}
