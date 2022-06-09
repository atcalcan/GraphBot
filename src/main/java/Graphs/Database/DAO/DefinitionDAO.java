package Graphs.Database.DAO;

import Graphs.Database.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefinitionDAO {


    public static Map<String, List<String>> getDefinitions(String query) {
        Statement statement;
        Map<String, List<String>> definitions = new HashMap<>();
        try {
            String sql = "SELECT * FROM DEFINITIONS WHERE NAME LIKE '%" + query + "%'";
            statement = DBManager.getInstance().getConnection().createStatement();
//            statement.setString(1, query);
//            statement.addBatch();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name);
                String def = resultSet.getString("def");
                definitions.putIfAbsent(name, new ArrayList<>());
                definitions.get(name).add(def);
            }
            System.out.println("1");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(definitions);
        return definitions;
    }

}
