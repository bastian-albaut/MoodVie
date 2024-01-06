package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Rating;
import com.moodvie.persistance.database.DatabaseConnection;
import java.sql.*;

/**
 * PostGreRatingDao est une implémentation de RatingDao spécifique à PostGre
 * Permet de gérer les notes
 */
public class PostGreRatingDao extends RatingDao {
    private final DatabaseConnection connection = DatabaseConnection.getInstance();

    public PostGreRatingDao() {
        createTable();
    }

    /**
     * Cette méthode supprime la table ratings de la base de données et toutes les tables qui en dépendent
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS subscribes CASCADE";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode crée la table ratings dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ratings (" +
                "id SERIAL PRIMARY KEY, " +
                "value INTEGER, " +
                "filmId VARCHAR(100), " +
                "userId INTEGER, " +
                "FOREIGN KEY (filmId) REFERENCES films(id)," +
                "FOREIGN KEY (userId) REFERENCES users(id)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }
    

}
