package Graphs;

import java.util.*;

public class Graph {
    private final String user;
    private String dir;
    private String name;
    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph(String user) {
        this.user = user;
        this.adjVertices = new HashMap<Vertex, List<Vertex>>();
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        if (this.dir.equalsIgnoreCase("undirected")) {
            if (adjVertices.get(v1) != null) {
                adjVertices.get(v1).add(v2);
            }
            if (adjVertices.get(v2) != null) {
                adjVertices.get(v2).add(v1);
            }
        } else if (this.dir.equalsIgnoreCase("directed")) {
            if (adjVertices.get(v1) != null) {
                adjVertices.get(v1).add(v2);
            }

        }
    }

    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    @Override
    public String toString() {

        return new GraphMatrix(this).toString();
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getDir() {
        return dir;
    }

    public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public List<Vertex> getAllVertices() {
        Set<Vertex> all = adjVertices.keySet();
        List<Vertex> result = new ArrayList<>(all);
        return result;
    }
}
