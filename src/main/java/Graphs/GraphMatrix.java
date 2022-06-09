package Graphs;

import java.util.*;

public class GraphMatrix {
    private final Map<Integer, Vertex> chei = new HashMap<>();
    private final int V;
    private int path[];
    private int color[];
    private final int[][] graph;


    public GraphMatrix(Graph g) {
        List<Vertex> noduri = g.getAllVertices();
        int ind = 0;
        Map<Vertex, Integer> cheiInvers = new HashMap<>();
        for (Vertex i : noduri) {
            chei.put(ind, i);
            cheiInvers.put(i, ind);
            ind++;
        }
        graph = new int[g.getAllVertices().size()][g.getAllVertices().size()];
        V = g.getAllVertices().size();
        for (int i = 0; i < V; i++) {
            Vertex indVer = chei.get(i);
            List<Vertex> adjIndVer = g.getVertexAdjVertices(indVer.getLabel());
            for (Vertex v : adjIndVer) {
                graph[cheiInvers.get(indVer)][cheiInvers.get(v)] = 1;
            }
        }
//        System.out.println(Arrays.deepToString(graph));
    }

    public int[][] getGraphMatrixInt() {
        return this.graph;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(graph);
    }

    boolean isSafeHamCycle(int v, int graph[][], int path[], int pos) {
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        return true;
    }

    boolean hamCycleUtil(int graph[][], int path[], int pos) {
        if (pos == V) {
            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }

        for (int v = 1; v < V; v++) {
            if (isSafeHamCycle(v, graph, path, pos)) {
                path[pos] = v;

                if (hamCycleUtil(graph, path, pos + 1))
                    return true;
                path[pos] = -1;
            }
        }

        return false;
    }

    boolean hamPathUtil(int graph[][], int path[], int pos) {
//        System.out.println(Arrays.toString(path));
        int cateGasite = V;
        for (int count = 0; count < V; count++) {
            if (path[count] == -1) {
                cateGasite--;
            }
        }
        if (cateGasite == V) {
            return true;
        }


        for (int v = 0; v < V; v++) {
            if (isSafeHamCycle(v, graph, path, pos)) {
                path[pos] = v;

                if (hamPathUtil(graph, path, pos + 1))
                    return true;
                path[pos] = -1;
            }
        }

        return false;
    }

    public String hamCycle() {
        path = new int[V];
        for (int i = 0; i < V; i++)
            path[i] = -1;
        path[0] = 0;
        if (!hamCycleUtil(graph, path, 1)) {
            return "NO";
        }
        return printSolutionHamCycle(path);
    }

    public String hamPath() {
        for (int start = 0; start < V; start++) {
            path = new int[V];
            for (int i = 0; i < V; i++)
                path[i] = -1;
            path[0] = start;
            if (hamPathUtil(graph, path, 1)) {
                return printSolutionHamPath(path);
            }
        }
        return "NO";
    }

    String printSolutionHamCycle(int path[]) {
        String solution = "";
        for (int i = 0; i < V; i++)
            solution = solution + chei.get(path[i]).getLabel() + " => ";

        solution = solution + chei.get(path[0]).getLabel();
        return solution;
    }

    String printSolutionHamPath(int path[]) {
        String solution = "";
        for (int i = 0; i < V - 1; i++)
            solution = solution + chei.get(path[i]).getLabel() + " => ";
        return solution + chei.get(path[V - 1]).getLabel();
    }


    boolean isSafeColoring(int v, int graph[][], int color[], int c) {
        for (int i = 0; i < V; i++)
            if (
                    graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    boolean graphColoringUtil(int graph[][], int m, int color[], int v) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafeColoring(v, graph, color, c)) {
                color[v] = c;

                if (
                        graphColoringUtil(
                                graph, m,
                                color, v + 1))
                    return true;

                color[v] = 0;
            }
        }
        return false;
    }

    public String graphColoringTest(int graph[][], int m) {
        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;

        if (
                !graphColoringUtil(
                        graph, m, color, 0)) {
            return "NO";
        }

        return printSolutionColoring(color);
    }

    String printSolutionColoring(int color[]) {
        String solution = "";
        for (int i = 0; i < V; i++)
            solution = solution + "nodul \"" + chei.get(i).getLabel() + "\": culoarea " + color[i] + ",\n";
        solution = solution.substring(0, solution.length() - 2);
        return solution;
    }

    public String getMaxColoring() {
        int m = V + 1;
        String rezultat;
        do {
            m = m - 1;
            rezultat = graphColoringTest(graph, m);
        } while (rezultat != "NO");
        m = m + 1;
        return m + ";" + graphColoringTest(graph, m);
    }


}
