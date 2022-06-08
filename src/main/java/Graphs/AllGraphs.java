package Graphs;

import java.util.*;

public class AllGraphs {
    private static List allGraphs = new ArrayList<Graph>();

    public AllGraphs() {

    }

    public static List getAllGraphs() {
        return allGraphs;
    }

    public static void addGraph(Graph g) {
        allGraphs.add(g);
    }

    public static String whatGraphs(String user) {
        String graphs = "";
//        for (Graph graph : allGraphs){
        for (Object allGraph : allGraphs) {
            Graph graph = (Graph) allGraph;
            if (Objects.equals(graph.getUser(), user)) {
                graphs = graphs + "*" + graph.getName() + "* ";
            }
        }
        return graphs;
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

    public static Set<String> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }

    public static Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }

}
