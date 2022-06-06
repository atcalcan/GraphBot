package Graphs;

import java.util.Objects;

public class Vertex {
    String label;

    Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
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
