package com.base.structure.map;

/**
 * @author 洪飞
 * @date 2020/6/20
 */
public interface Map<K, V> {

    /**
     * 获取map的大小
     *
     * @return
     */
    int size();

    /**
     * 判断map是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 清空map
     */
    void clear();

    /**
     * 往map中放入key以及对应的value
     *
     * @param key   待放入的key
     * @param value 待放入的value
     * @return 返回放入的值
     */
    V put(K key, V value);

    /**
     * 根据key查找对应的value
     *
     * @param key 待查找的key
     * @return 返回查找到的value
     */
    V get(K key);

    /**
     * 根据key删除对应数据
     *
     * @param key 待删除的key
     * @return 返回删除的key对应的value
     */
    V remove(K key);

    /**
     * 判断map中是否包含某个key
     *
     * @param key 待判断的key
     * @return true表示包含，false表示不包含
     */
    boolean containsKey(K key);

    /**
     * 判断map中是否包含某个value
     *
     * @param value 待判断的value
     * @return true表示包含，false表示不包含
     */
    boolean containsValue(V value);

    /**
     * 遍历map
     *
     * @param visitor 遍历时候采取的操作
     */
    void traversal(Visitor<K, V> visitor);

    /**
     * 遍历器
     *
     * @param <K> 遍历使用的key
     * @param <V> 遍历使用的value
     */
    public static abstract class Visitor<K, V> {
        /**
         * true表示遍历终止，false表示遍历继续
         */
        boolean stop;

        /**
         * 遍历元素
         *
         * @param key   遍历的元素的key
         * @param value 遍历的元素的value
         * @return true表示遍历终止，false表示继续遍历
         */
        public abstract boolean visit(K key, V value);
    }
}
