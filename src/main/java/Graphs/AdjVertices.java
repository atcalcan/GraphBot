package Graphs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class AdjVertices {
    @JsonProperty
    private Map<Vertex, List<Vertex>> adjVertices;

    @JsonCreator
    public AdjVertices(@JsonProperty("adjVertices") Map<Vertex, List<Vertex>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    public Map<Vertex, List<Vertex>> getAdjVertices() {
        return adjVertices;
    }
}
