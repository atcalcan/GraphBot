package Graphs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Vertex {
    @JsonProperty
    final String label;

    @JsonCreator
    Vertex(@JsonProperty("label") String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(label);
    }

    @Override
    public boolean equals(Object obj) {
        return label.equals(((Vertex) obj).label);
    }

}
