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
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,user,password);
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error connecting to the database!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
