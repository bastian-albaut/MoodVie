package persistance.dao;

import pw.mihou.dotenv.Dotenv;
import pw.mihou.dotenv.types.NormalDotenv;
import java.sql.*;

public class DatabaseConnection {

    private NormalDotenv dotenv = Dotenv.as();
    private String url = dotenv.get("JDBC_DATABASE_URL");
    private String user = dotenv.get("JDBC_DATABASE_USERNAME");
    private String password = dotenv.get("JDBC_DATABASE_PASSWORD");
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user,password);
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return connection;
    }
}
