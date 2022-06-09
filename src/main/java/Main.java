import Graphs.Database.DAO.GraphDAO;
import Graphs.Graph;

public class Main {
    public static void main(String[] args) {
//        try {
//            Graph g = new Graph("trial");
//            g.setName("g102");
//            g.setDir("undirected");
//            g.addVertex("1");
//            g.addVertex("2");
//            g.addEdge("1", "2");
//            ObjectMapper mapper = new ObjectMapper();
//            String test = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(g);
//            System.out.println(test);
//
//            String test2 = "{\n" +
//                    "  \"user\" : \"trial\",\n" +
//                    "  \"dir\" : \"undirected\",\n" +
//                    "  \"name\" : \"g102\",\n" +
//                    "  \"adjVertices\" : {\n" +
//                    "    \"1\" : [ {\n" +
//                    "      \"label\" : \"2\"\n" +
//                    "    } ],\n" +
//                    "    \"2\" : [ {\n" +
//                    "      \"label\" : \"1\"\n" +
//                    "    } ]\n" +
//                    "  },\n" +
//                    "  \"allVertices\" : [ {\n" +
//                    "    \"label\" : \"1\"\n" +
//                    "  }, {\n" +
//                    "    \"label\" : \"2\"\n" +
//                    "  } ]\n" +
//                    "}";
//            Graph g2 = mapper.readValue(test2, Graph.class);
//            System.out.println(g2);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        Graph g = new Graph("mayhemer");
        g.setName("g102");
        g.setDir("undirected");
        g.addVertex("1");
        g.addVertex("2");
        g.addEdge("1", "2");
        GraphDAO.AddGraph(g);
//
    }
}
