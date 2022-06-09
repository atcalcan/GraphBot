package Graphs.Database;

import java.sql.*;

public class DBManager {
    private Connection connection;
    private static DBManager instance = new DBManager();

    private DBManager() {
        String url = System.getenv("ENV_DB_URL");
        String user = System.getenv("ENV_DB_UNAME");
        String pass = System.getenv("ENV_DB_PASS");
        try {
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBManager getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
