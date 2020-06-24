package com.base.structure.graph;

import com.base.structure.iter.Visitor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 图的接口定义
 *
 * @param <V> 顶点
 * @param <E> 边
 */
public interface Graph<V, E> {

    /**
     * 获取顶点的个数
     *
     * @return 返回顶点的个数
     */
    int vertexSize();

    /**
     * 获取边的个数
     *
     * @return 返回边的个数
     */
    int edgeSize();

    /**
     * 添加顶点
     *
     * @param v 顶点的值
     */
    void addVertex(V v);

    /**
     * 添加边-不带权重
     *
     * @param from 边的起点
     * @param to   边的终点
     */
    void addEdge(V from, V to);

    /**
     * 添加边-带权重
     *
     * @param from   边的起点
     * @param to     边的终点
     * @param weight 边的权重
     */
    void addEdge(V from, V to, E weight);

    /**
     * 删除顶点
     *
     * @param v 待删除的顶点
     */
    void removeVertex(V v);

    /**
     * 删除边
     *
     * @param from 边的起点
     * @param to   边的终点
     */
    void removeEdge(V from, V to);

    /**
     * 广度优先遍历
     *
     * @param begin   遍历的起点
     * @param visitor 遍历出来的元素进行的操作
     */
    void bfs(V begin, Visitor visitor);

    /**
     * 深度优先遍历
     *
     * @param begin   遍历的起点
     * @param visitor 遍历出来的元素进行的操作
     */
    void dfs(V begin, Visitor visitor);

    /**
     * 拓扑排序，前提是该图必须是有向无环图
     *
     * @return 返回拓扑排序的结果
     */
    List<V> topologicalSort();

    /**
     * 生成最小生成树(Minimum Spanning Tree)，也叫做最小权重生成树、最小支撑树
     * 是所有生成树中，边的总权值最小的那颗
     *
     * @return 返回最小生成树中所有的边
     */
    Set<EdgeInfo<V, E>> mst();

    /**
     * 单源最短路径-简单版
     *
     * @param begin 源点
     * @return 返回从源点到每个顶点的最短路径值，其中key是除了源点之外的其他顶点
     */
    Map<V, E> shortestPath(V begin);

    /**
     * 单源最短路径-进化版，该方法是{@link Graph#shortestPath(java.lang.Object)}的进化版本
     * 使用dijkstra算法
     *
     * @param begin 源点
     * @return 返回从源点到每个顶点的最短路径值，其中key是除了源点之外的其他顶点
     */
    Map<V, PathInfo<V, E>> dijkstra(V begin);

    /**
     * 单源最短路径-进化版，该方法是{@link Graph#shortestPath(java.lang.Object)}的进化版本
     * 使用bellman-ford算法
     *
     * @param begin 源点
     * @return 返回从源点到每个顶点的最短路径值，其中key是除了源点之外的其他顶点
     */
    Map<V, PathInfo<V, E>> bellmanFord(V begin);

    /**
     * 多源最短路径算法，使用Floyd算法
     *
     * @return 返回每个顶点与其他顶点的最短路径，其中key是起点，value.key是终点，value.value是路径
     */
    Map<V, Map<V, PathInfo<V, E>>> floyd();

    /**
     * 将图打印出来
     */
    void print();

    /**
     * 最小生成树算法中返回的边的信息，因为要与具体实现解耦
     *
     * @param <V> 顶点
     * @param <E> 边的权重
     */
    @Data
    class EdgeInfo<V, E> {
        /**
         * 起点
         */
        V from;
        /**
         * 终点
         */
        V to;
        /**
         * 权重
         */
        E weight;

        protected EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * 在计算最短路径的时候使用，用于返回最短路径的信息
     *
     * @param <V> 顶点的值
     * @param <E> 边的信息
     */
    @Data
    class PathInfo<V, E> {
        /**
         * 最短路径的权重
         */
        E weight;
        /**
         * 最短路径上所经历的边的信息
         */
        List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();

        public PathInfo() {
        }

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public PathInfo(E weight, List<EdgeInfo<V, E>> edgeInfos) {
            this.weight = weight;
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", edgeInfos=" + edgeInfos +
                    '}';
        }
    }
}
