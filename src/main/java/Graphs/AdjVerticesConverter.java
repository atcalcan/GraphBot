package Graphs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
public class AdjVerticesConverter implements AttributeConverter<Map<Vertex, List<Vertex>>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Vertex, List<Vertex>> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<Vertex, List<Vertex>> convertToEntityAttribute(String dbData) {
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            return objectMapper.readValue(dbData, typeFactory.constructMapType(HashMap.class, Vertex.class, Class.forName(String.valueOf(typeFactory.constructCollectionType(List.class, Vertex.class)))));
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to map", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
