package com.base.structure.graph;

import com.base.structure.graph.weight.WeightManager;
import com.base.structure.heap.MinHeap;
import com.base.structure.iter.Visitor;
import com.base.structure.union.GenericUnionFind;

import java.util.*;

/**
 * 图的邻接表实现
 *
 * @param <V> 顶点
 * @param <E> 边
 */
@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends AbstractGraph<V, E> {

    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    private Comparator<Edge<V, E>> edgeComparator = (e1, e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    /**
     * 存放所有的顶点的值和其对应的顶点
     */
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    /**
     * 存放所有的边
     */
    private Set<Edge<V, E>> edges = new HashSet<>();

    @Override
    public void print() {
        vertices.forEach((v, vertex) -> System.out.println(v));
        edges.forEach((edge) -> System.out.println(edge));
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        //这里如果要采用set遍历的方法太麻烦，因为Edge实现了hashCode和equals方法
        //所以不管是否存在，都直接删除，然后再次添加即可。
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.get(v);
        if (vertex == null) return;

        Vertex<V, E> remove = vertices.remove(v);
        if (remove != null) {
            Set<Edge<V, E>> outEdges = remove.outEdges;
            if (!outEdges.isEmpty()) {
                outEdges.forEach((edge) -> {
                    edge.to.inEdges.remove(edge);
                    edges.remove(edge);
                });
            }

            Set<Edge<V, E>> inEdges = remove.inEdges;
            if (!inEdges.isEmpty()) {
                inEdges.forEach((edge) -> {
                    edge.from.outEdges.remove(edge);
                    edges.remove(edge);
                });
            }
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, Visitor visitor) {
        if (visitor == null) return;

        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) return;
            visitedVertices.add(vertex);

            for (Edge<V, E> outEdge : vertex.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.offer(outEdge.to);
            }

        }
    }

    @Override
    public void dfs(V begin, Visitor visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();

        dfsIter(beginVertex, visitedVertices, visitor);
    }

    /**
     * 递归深度遍历
     *
     * @param beginVertex     遍历开始的点
     * @param visitedVertices 遍历过的点的集合
     * @param visitor         遍历的点的操作
     */
    private void dfsRecur(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visitedVertices, Visitor visitor) {
        if (beginVertex == null) return;

        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);
        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            if (visitedVertices.contains(outEdge.to)) continue;
            dfsRecur(outEdge.to, visitedVertices, visitor);
        }
    }

    /**
     * 迭代深度遍历
     *
     * @param beginVertex     遍历开始的点
     * @param visitedVertices 遍历过的点的集合
     * @param visitor         遍历的点的操作
     */
    private void dfsIter(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visitedVertices, Visitor visitor) {
        if (beginVertex == null) return;

        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        visitor.visit(beginVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            stack.push(vertex);
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;

                //将选择的边的from和to都入栈
                stack.push(vertex);
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                if (visitor.visit(edge.to.value)) return;
                break;
            }
        }
    }

    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();

        //构建一个入度表，存放每个节点的入度
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        //构建一个queue，依次放入入度为0的顶点
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, in);
            }
        });

        //从queue中拿出入度为0的顶点，将其对应的边的另外一个顶点的入度减少1，并将该点放入到list中
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            vertex.outEdges.forEach(edge -> {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            });
        }

        return list;
    }


    @Override
    public Set<EdgeInfo<V, E>> mst() {
        //随机，保证两个算法都能够被使用
        return Math.random() > 0.5 ? mstWithPrim() : mstWithKruskal();
    }

    /**
     * 使用Prim算法生成最小生成树
     * 其原理是基于切分定理：给定任意切分，横切边中权值最小的边必然属于最小生成树
     *
     * @return 返回最小生成树的边的集合
     */
    private Set<EdgeInfo<V, E>> mstWithPrim() {
        Iterator<Vertex<V, E>> vertexIterator = vertices.values().iterator();
        if (!vertexIterator.hasNext()) return Collections.emptySet();

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();

        Vertex<V, E> vertex = vertexIterator.next();
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        int edgeSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) continue;

            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    /**
     * 使用Kruskal算法生成最小生成树
     * 其原理是：按照边的权重顺序（从小到大）将边加入到生成树中，直到生成树中含有V-1条边为止
     * 有两种情况需要注意：1、若加入该边会和生成树形成环，则不加入该边。2、从第三条边开始可能会和生成树形成环，因为两条边是不能形成环的。
     * <p>
     * 判断是否有环，可以使用并查集，因为如果选中一个边，它的起点和终点都在一个集合中，那么它一定构成了一个环
     *
     * @return 返回最小生成树的边的集合
     */
    private Set<EdgeInfo<V, E>> mstWithKruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return Collections.emptySet();

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);

        //将所有的顶点构成只有单个节点的并查集
        GenericUnionFind<Vertex<V, E>> unionFind = new GenericUnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> unionFind.makeSet(vertex));

        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();

            //使用并查集看看该边的from和to是否已经在一个集合中，如果是，表示添加该边就会出现环，需要continue
            if (unionFind.isSame(edge.from, edge.to)) continue;

            edgeInfos.add(edge.info());
            //在添加边之后，需要将顶点加入到并查集中
            unionFind.union(edge.from, edge.to);
        }
        return edgeInfos;
    }

    @Override
    public Map<V, E> shortestPath(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return Collections.emptyMap();

        //记录下已经被拉起来的顶点
        Map<V, E> selectedPaths = new HashMap<>();
        //记录下剩下的顶点的路径
        Map<Vertex<V, E>, E> paths = new HashMap<>();
        //初始化paths
        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            paths.put(outEdge.to, outEdge.weight);
        }

        //循环进行选择最短路径和松弛操作
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
            //将选择出来的最小路径和顶点拉起来，放到最短路径中
            Vertex<V, E> minVertex = minEntry.getKey();
            selectedPaths.put(minVertex.value, minEntry.getValue());
            paths.remove(minVertex);

            //对选择出来的顶点进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                //如果edge.to已经在最短路径中，就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to)) continue;
                //新的可选择的最短路径：beginVertex到edge.from的最短路径+edge.weight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                //以前的最短路径：beginVertex到edge.to的最短路径
                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }
            }
        }
        selectedPaths.remove(beginVertex);
        return selectedPaths;
    }

    /**
     * 从paths中挑选出来一个最小的路径的键值对，即顶点和边
     *
     * @param paths 待选路径集合
     * @return 返回最短路径的键值对
     */
    private Map.Entry<Vertex<V, E>, E> getMinPath(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> entry = iterator.next();
            if (weightManager.compare(entry.getValue(), minEntry.getValue()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    @Override
    public Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return Collections.emptyMap();

        //记录下已经被拉起来的顶点
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        //记录下剩下的顶点的路径
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        //初始化paths
        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            PathInfo<V, E> pathInfo = new PathInfo<>();
            pathInfo.setWeight(outEdge.weight);
            pathInfo.edgeInfos.add(outEdge.info());
            paths.put(outEdge.to, pathInfo);
        }

        //循环进行选择最短路径和松弛操作
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinDijsktraPath(paths);
            //将选择出来的最小路径和顶点拉起来，放到最短路径中
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);

            //对选择出来的顶点进行松弛操作
            for (Edge<V, E> edge : minVertex.outEdges) {
                //如果edge.to已经在最短路径中，就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to)) continue;
                relaxForDijkstra(edge, minPath, paths);
            }
        }
        selectedPaths.remove(beginVertex);
        return selectedPaths;
    }

    @Override
    public Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return Collections.emptyMap();

        //记录下已经被拉起来的顶点
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        /**这里添加默认值是为了解决头顶点的问题，否则下面的{@link com/base/structure/graph/ListGraph.java:432}会一直满足，一直在空跑*/
        selectedPaths.put(begin, new PathInfo<>(weightManager.zero()));

        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) {
            //这里循环v-1次，按照BellmanFord的理论，经过v-1次（最坏情况）会出现最短路径
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) continue;
                relaxForBellmanFord(edge, fromPath, selectedPaths);
            }
        }

        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
            if (fromPath == null) continue;
            //在这里，因为上面的循环中已经重复了v-1次，那么这个时候如果还能找到，说明有负权环，找不到最短路径
            if (relaxForBellmanFord(edge, fromPath, selectedPaths)) return null;
        }

        return selectedPaths;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> floyd() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        //初始化
        for (Edge<V, E> edge : edges) {
            Map<V, PathInfo<V, E>> infoMap = paths.get(edge.from.value);
            if (infoMap == null) {
                infoMap = new HashMap<>();
                paths.put(edge.from.value, infoMap);
            }
            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            infoMap.put(edge.to.value, pathInfo);
        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) return;
                    // v1 -> v2
                    PathInfo<V, E> path1To2 = getPathInfo(v1, v2, paths);
                    if (path1To2 == null) return;
                    // v2 -> v3
                    PathInfo<V, E> path2To3 = getPathInfo(v2, v3, paths);
                    if (path2To3 == null) return;
                    // v1 -> v3
                    PathInfo<V, E> path1To3 = getPathInfo(v1, v3, paths);

                    E newWeight = weightManager.add(path1To2.weight, path2To3.weight);
                    if (path1To3 != null && weightManager.compare(newWeight, path1To3.weight) >= 0) return;

                    if (path1To3 == null) {
                        path1To3 = new PathInfo<>();
                        paths.get(v1).put(v3, path1To3);
                    } else {
                        path1To3.edgeInfos.clear();
                    }

                    path1To3.weight = newWeight;
                    path1To3.edgeInfos.addAll(path1To2.edgeInfos);
                    path1To3.edgeInfos.addAll(path2To3.edgeInfos);
                });
            });
        });

        return paths;
    }

    private PathInfo<V, E> getPathInfo(V v1, V v2, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> infoMap = paths.get(v1);
        return infoMap == null ? null : infoMap.get(v2);
    }

    /**
     * 将松弛操作独立封装，在其他算法中调用
     *
     * @param edge     需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths    存放着其他（对dijkstra来说，就是还没有离开桌面的点）点的最短路径信息
     * @return true表示松弛成功，false表示松弛失败
     */
    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        //新的可选择的最短路径：beginVertex到edge.from的最短路径+edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
        return true;
    }

    /**
     * 将松弛操作独立封装，在其他算法中调用
     *
     * @param edge     需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths    存放着其他（对dijkstra来说，就是还没有离开桌面的点）点的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        //新的可选择的最短路径：beginVertex到edge.from的最短路径+edge.weight
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, E> oldPath = paths.get(edge.to);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 从paths中挑选出来一个最小的路径的键值对，即顶点和边
     *
     * @param paths 待选路径集合
     * @return 返回最短路径的键值对
     */
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinDijsktraPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> entry = iterator.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }
}
