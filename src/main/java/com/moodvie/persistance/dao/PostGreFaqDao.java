package com.moodvie.persistance.dao;
import com.moodvie.persistance.model.Faq;
import com.moodvie.persistance.model.Subscribe;
import com.moodvie.persistance.database.DatabaseConnection;

import java.sql.*;


public class PostGreFaqDao extends FaqDao {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreFaqDao() {
        dropTable();
        createTable();
    }

    /**
     * Cette méthode supprime la table faq de la base de données et toutes les tables qui en dépendent
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS faq CASCADE";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode crée la table faq dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS faq (" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }


    /**
     * Cette méthode ajoute un abonnement dans la base de données
     * @param faq l'abonnement à ajouter
     */
    @Override
    public void addFaq(Faq faq) {
        
        // TODO
    }
}
