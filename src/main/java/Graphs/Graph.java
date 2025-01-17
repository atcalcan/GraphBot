package Graphs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "graphs")
public class Graph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;

    @JsonProperty
    private String dir;

    @JsonProperty
    @Column(unique = true, nullable = false)
    private String name;

    @JsonProperty
    @ElementCollection
    @CollectionTable(name = "graph_adj_vertices", joinColumns = @JoinColumn(name = "graph_id"))
    @MapKeyJoinColumn(name = "vertex_id")
    @Column(name = "adj_vertices")
    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    // Constructors, getters, and setters

    public Graph() {
    }

    public Graph(String user) {
        this.user = user;
        this.adjVertices = new HashMap<>();
    }

    @JsonCreator
    public Graph(@JsonProperty("user") String user, @JsonProperty("dir") String dir, @JsonProperty("name") String name, @JsonProperty("adjVertices") Map<Vertex, List<Vertex>> adjVertices) {
        this.user = user;
        this.dir = dir;
        this.name = name;
        this.adjVertices = adjVertices;
    }

    public Map<Vertex, List<Vertex>> getAdjVertices() {
        return adjVertices;
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }

    public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> all = this.getAllVertices();
        int OK1 = 0, OK2 = 0;
        for (Vertex i : all) {
            if (i.equals(v1)) {
                OK1 = 1;
            }
            if (i.equals(v2)) {
                OK2 = 1;
            }
        }
        if (OK1 == 1 && OK2 == 1) {
            if (this.dir.equalsIgnoreCase("undirected")) {
                adjVertices.get(v1).add(v2);
                adjVertices.get(v2).add(v1);
            } else if (this.dir.equalsIgnoreCase("directed")) {
                adjVertices.get(v1).add(v2);
            }
        } else {
            throw new IllegalArgumentException("Unul dintre noduri (" + label1 + ", " + label2 + ") nu există.");
        }
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        if (adjVertices.get(v1).contains(v2)) {
            adjVertices.get(v1).remove(v2);
        }
        if (adjVertices.get(v2).contains(v1)) {
            adjVertices.get(v2).remove(v1);
        }
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

    public List<Vertex> getVertexAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    @JsonIgnore
    public List<Vertex> getAllVertices() {
        Set<Vertex> all = adjVertices.keySet();
        return new ArrayList<>(all);
    }

    @JsonIgnore
    public String getAdjVerticesAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(adjVertices);
    }

    @JsonIgnore
    public void setAdjVerticesFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.adjVertices = mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, Vertex.class, List.class));
    }
}
