package com.base.structure.graph;

import com.base.structure.graph.weight.WeightManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphTest {

    public static void main(String[] args) {
        testSp();
    }

    static WeightManager<Double> weightManager = new WeightManager<Double>() {
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

//    static void testMultiSp() {
//        Graph<Object, Double> graph = directedGraph(GraphData.NEGATIVE_WEIGHT1);
//        Map<Object, Map<Object, PathInfo<Object, Double>>> sp = graph.shortestPath();
//        sp.forEach((Object from, Map<Object, PathInfo<Object, Double>> paths) -> {
//            System.out.println(from + "---------------------");
//            paths.forEach((Object to, PathInfo<Object, Double> path) -> {
//                System.out.println(to + " - " + path);
//            });
//        });
//    }

    static void testSp() {
        Graph<Object, Double> graph = directedGraph(GraphData.SP);
        Map<Object, Graph.PathInfo<Object, Double>> sp = graph.dijkstra("A");
        if (sp == null) return;
        sp.forEach((Object v, Graph.PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }

    static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(GraphData.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.mst();
        for (Graph.EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void testTopo() {
        Graph<Object, Double> graph = directedGraph(GraphData.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

//    static void testDfs() {
//        Graph<Object, Double> graph = directedGraph(GraphData.DFS_02);
//        graph.dfs("a", (Object v) -> {
//            System.out.println(v);
//            return false;
//        });
//    }
//
//    static void testBfs() {
//        Graph<Object, Double> graph = directedGraph(GraphData.BFS_02);
//        graph.bfs(0, (Object v) -> {
//            System.out.println(v);
//            return false;
//        });
//    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] GraphData) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : GraphData) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     *
     * @param GraphData
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] GraphData) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : GraphData) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}