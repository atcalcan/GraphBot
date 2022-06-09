package Graphs.Database.DAO;

import Graphs.AdjVertices;
import Graphs.Database.DBManager;
import Graphs.Graph;
import Graphs.Vertex;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDAO {


    public static List<Graph> getAllDBGraphs() {
        Statement statement;
        List<Graph> allGraphs = new ArrayList<>();
        try {
            statement = DBManager.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GRAPHS");
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String name = resultSet.getString("name");
                String dir = resultSet.getString("dir");
                String adjs = resultSet.getString("adjs");
                Map<Vertex, List<Vertex>> adjVertex = new HashMap<>();
                ObjectMapper mapper = new ObjectMapper();
                AdjVertices temp;
                temp = mapper.readValue(adjs, AdjVertices.class);
                Graph g = new Graph(user, dir, name, temp.getAdjVertices());
                allGraphs.add(g);
            }

            statement.close();
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(allGraphs);
        return allGraphs;
    }

    public static void AddGraph(Graph g) {
        List<Graph> all = getAllDBGraphs();
        for (Graph ind : all) {
            if (ind.getName().equals(g.getName()) && ind.getUser().equals(g.getUser()))
//            System.out.println("3");
                RemoveGraph(g);
        }

        PreparedStatement statement;
        try {
            ObjectMapper mapper = new ObjectMapper();
            AdjVertices adjVertex = new AdjVertices(g.getAdjVertices());
            String adj = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(adjVertex);

            String sql = "INSERT INTO GRAPHS VALUES (?, ?, ?, ?)";
            statement = DBManager.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, g.getUser());
            statement.setString(2, g.getDir());
            statement.setString(3, g.getName());
            statement.setString(4, adj);
            statement.addBatch();
            statement.executeBatch();
            statement.close();
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void RemoveGraph(Graph g) {
//        System.out.println("1");
        PreparedStatement statement;
        try {
            String sql = "DELETE FROM GRAPHS WHERE NAME = ?";
            statement = DBManager.getInstance().getConnection().prepareStatement(sql);

            statement.setString(1, g.getName());
            statement.addBatch();
            statement.executeBatch();
            statement.close();
//            System.out.println("2");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
