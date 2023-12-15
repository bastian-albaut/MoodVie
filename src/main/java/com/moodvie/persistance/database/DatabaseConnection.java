package com.moodvie.model.persistance.database;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import pw.mihou.dotenv.Dotenv;
import pw.mihou.dotenv.types.NormalDotenv;
import java.sql.*;



/**
 * DatabaseConnection is a singleton
 */
public class DatabaseConnection {

    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);

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
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                logger.error("Error while connecting to the database : " + e.getMessage());
            }
        }
        return connection;
    }
}
