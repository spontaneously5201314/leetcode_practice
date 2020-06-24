package com.base.structure.graph;

import com.base.structure.graph.weight.WeightManager;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 图的抽象类
 *
 * @param <V> 顶点
 * @param <E> 边
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

    protected WeightManager<E> weightManager;

    public AbstractGraph() {
    }

    public AbstractGraph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    protected static class Vertex<V, E> {
        V value;
        /**
         * 入度的边
         */
        Set<Edge<V, E>> inEdges = new HashSet<>();
        /**
         * 出度的边
         */
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return value.equals(vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }
    }

    protected static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }
    }
}
