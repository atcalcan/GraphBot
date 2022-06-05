package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private String user;
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
        if (adjVertices.get(v1) != null) {
            adjVertices.get(v1).add(v2);
        }
        if (adjVertices.get(v2) != null) {
            adjVertices.get(v2).add(v1);
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

    Graph() {

    }

    @Override
    public String toString() {

        return super.toString();
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

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }
}
