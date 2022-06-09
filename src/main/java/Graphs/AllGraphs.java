package Graphs;

import Graphs.Database.DAO.GraphDAO;

import java.util.*;

public class AllGraphs {
    public static List allGraphs = GraphDAO.getAllDBGraphs();

//    public AllGraphs() {
//
//    }

    public static List getAllGraphs() {
        return allGraphs;
    }

    public static void addGraph(Graph g) {
        allGraphs.add(g);
    }

    public static String whatGraphs(String user) {
        try {
            String graphs = "";
            for (Object allGraph : allGraphs) {
                Graph graph = (Graph) allGraph;
                if (Objects.equals(graph.getUser(), user)) {
                    graphs = graphs + "*" + graph.getName() + "*, ";
                }
            }
            graphs = graphs.substring(0, graphs.length() - 2);
            return graphs;
        } catch (StringIndexOutOfBoundsException exception) {
            return "";
        }
    }

    public static boolean checkGraph(String user, String name) {
        for (Object allGraph : allGraphs) {
            Graph graph = (Graph) allGraph;
            if (Objects.equals(graph.getUser(), user) && Objects.equals(graph.getName(), name)) {
                return true;
            }
        }
        return false;
    }

    public static Graph getGraph(String name) {
        for (Object allGraph : allGraphs) {
            Graph graph = (Graph) allGraph;
            if (Objects.equals(graph.getName(), name)) {
                return graph;
            }
        }
        return null;
    }

    static String DFSUtil(int v, boolean[] visited, Graph g, String rezultat, Map<Integer, Vertex> cheiInverse, Map<Vertex, Integer> chei) {
        visited[v] = true;
        rezultat = rezultat + cheiInverse.get(v).getLabel() + ", ";
//        System.out.println(rezultat);

        List<Vertex> veciniV = g.getVertexAdjVertices(cheiInverse.get(v).getLabel());
        List<Integer> intVeciniV = new LinkedList<>();
        for (Vertex vecin : veciniV) {
            intVeciniV.add(chei.get(vecin));
        }
//        intVeciniV.sort(Comparator.naturalOrder());
        for (int n : intVeciniV) {
            if (!visited[n])
                rezultat = DFSUtil(n, visited, g, rezultat, cheiInverse, chei);
        }
        return rezultat;
    }

    public static String DFS(Graph g, String v) {
        String rezultat = "[";
        Map<Vertex, Integer> chei = new HashMap<>();
        Map<Integer, Vertex> cheiInverse = new HashMap<>();
        int V = g.getAllVertices().size();
        int ind = 0;
        for (Vertex i : g.getAllVertices()) {
            chei.putIfAbsent(i, ind);
            cheiInverse.putIfAbsent(ind, i);
            ind++;
        }

        boolean[] visited = new boolean[V];
        Vertex start = new Vertex(v);

        String output = DFSUtil(chei.get(start), visited, g, rezultat, cheiInverse, chei);
//        System.out.println(output);
        output = output.substring(0, output.length() - 2) + "]";
        return output;
    }

    public static String BFS(Graph g, String s) {
        String rezultat = "[";
        Map<Vertex, Integer> chei = new HashMap<>();
        Map<Integer, Vertex> cheiInverse = new HashMap<>();
        int V = g.getAllVertices().size();
        int ind = 0;
        for (Vertex i : g.getAllVertices()) {
            chei.putIfAbsent(i, ind);
            cheiInverse.putIfAbsent(ind, i);
            ind++;
        }
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Vertex startV = new Vertex(s);
        int start = chei.get(startV);

        visited[start] = true;
        queue.add(start);

        while (queue.size() != 0) {
            start = queue.poll();
            rezultat = rezultat + cheiInverse.get(start).getLabel() + ", ";

            List<Vertex> veciniV = g.getVertexAdjVertices(cheiInverse.get(start).getLabel());
            List<Integer> intVeciniV = new LinkedList<>();
            for (Vertex vecin : veciniV) {
                intVeciniV.add(chei.get(vecin));
            }
//            intVeciniV.sort(Comparator.naturalOrder());
            for (int n : intVeciniV) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        rezultat = rezultat.substring(0, rezultat.length() - 2) + "]";
        return rezultat;
    }

}
