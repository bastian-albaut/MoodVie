package com.moodvie.persistance.dao;
import com.moodvie.persistance.model.Subscribe;
import com.moodvie.persistance.database.DatabaseConnection;

import java.sql.*;


/**
 * PostGreSubscribeDao est une implémentation de SubscribeDao spécifique à PostGre
 * Permet de gérer les abonnements
 */
public class PostGreSubscribeDao extends SubscribeDao {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreSubscribeDao() {
        dropTable();
        createTable();
    }

    /**
     * Cette méthode supprime la table subscribes de la base de données
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS subscribes";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode crée la table subscribes dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS subscribes (" +
                "id SERIAL PRIMARY KEY, " +
                "startDate TIMESTAMP, " +
                "isActive BOOLEAN, " +
                "typeOfSubscribeId INTEGER, " +
                "userId INTEGER, " +
                "FOREIGN KEY (typeOfSubscribeId) REFERENCES typeOfSubscribes(id)," +
                "FOREIGN KEY (userId) REFERENCES users(id)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }


    /**
     * Cette méthode ajoute un abonnement dans la base de données
     * @param subscribe l'abonnement à ajouter
     */
    @Override
    public void addSubscribe(Subscribe subscribe) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO subscribes (id, startDate, isActive, typeOfSubscribeId, userId) VALUES (?, ?, ?, ?, ?)")) {
            setSubscribe(subscribe, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode permet de définir les paramètres d'un abonnement dans une requête SQL
     * @param subscribe l'abonnement à ajouter
     * @param ps la requête SQL à modifier
     */
    private void setSubscribe(Subscribe subscribe, PreparedStatement ps) throws SQLException {
        ps.setInt(1, subscribe.getId());
        ps.setTimestamp(2, subscribe.getStartDate());
        ps.setBoolean(3, subscribe.getIsActive());
        ps.setInt(4, subscribe.getTypeOfSubscribeId());
        ps.setInt(5, subscribe.getUserId());
    }

    /**
     * Cette méthode récupère un abonnement dans la base de données
     * @param email l'email de l'utilisateur ayant l'abonnement
     * @return l'abonnement récupéré
     */
    public Subscribe getSubscribe(String email) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM subscribes NATURAL JOIN users WHERE email = ?");
            ps.setString(1, email);
            Subscribe subscribe = getSubscribe(ps);
            if (subscribe != null) return subscribe;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Cette méthode récupère un abonnement dans la base de données
     * @param ps la requête SQL à exécuter
     * @return l'abonnement récupéré
     */
    private Subscribe getSubscribe(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Subscribe subscribe = new Subscribe();
            subscribe.setId(rs.getInt("id"));
            subscribe.setStartDate(rs.getTimestamp("startDate"));
            subscribe.setIsActive(rs.getBoolean("isActive"));
            subscribe.setTypeOfSubscribeId(rs.getInt("typeOfSubscribeId"));
            subscribe.setUserId(rs.getInt("userId"));

            return subscribe;
        }
        return null;
    }

    /**
     * Cette méthode met à jour un abonnement dans la base de données
     * @param subscribe l'abonnement à mettre à jour
     */
    @Override
    public void updateSubscribe(Subscribe subscribe) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE subscribes SET id = ?, startDate = ?, isActive = ?, typeOfSubscribeId = ?, userId = ? WHERE id = ?");
            setSubscribe(subscribe, ps);
            ps.setInt(7, subscribe.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode récupère un abonnement dans la base de données
     * @param subscribeId l'identifiant de l'abonnement à récupérer
     * @return l'abonnement récupéré
     */
    public Subscribe getSubscribe(int subscribeId) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM subscribes WHERE id = ?");
            ps.setInt(1, subscribeId);
            Subscribe subscribe = getSubscribe(ps);
            if (subscribe != null) return subscribe;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Cette méthode supprime un abonnement de la base de données
     * @param subscribeId l'identifiant de l'abonnement à supprimer
     */
    public void deleteSubscribe(int subscribeId) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM subscribes WHERE id = ?");
            ps.setInt(1, subscribeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
