package persistance.dao;

import pw.mihou.dotenv.Dotenv;
import pw.mihou.dotenv.types.NormalDotenv;
import java.sql.*;

/**
 * DatabaseConnection is a singleton
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;

    private final NormalDotenv dotenv = Dotenv.as();
    private final String url = dotenv.get("JDBC_DATABASE_URL");
    private final String user = dotenv.get("JDBC_DATABASE_USERNAME");
    private final String password = dotenv.get("JDBC_DATABASE_PASSWORD");
    private Connection connection;

    private DatabaseConnection() {}

    /**
     * Permet de récupérer une instance de DatabaseConnection
     * @return une instance de DatabaseConnection
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Permet de récupérer une connexion à la base de données
     * @return une connexion à la base de données
     */
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
