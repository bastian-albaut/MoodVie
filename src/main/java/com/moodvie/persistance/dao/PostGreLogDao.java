package com.moodvie.persistance.dao;

import com.moodvie.persistance.database.DatabaseConnection;
import com.moodvie.persistance.model.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGreLogDao extends LogDao{
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreLogDao() {
        createTable();
        add(new Log("Création de la table log", "1", "admin"));
        add(new Log("Création de la table users", "1", "admin"));
        add(new Log("Création de la table movies", "1", "admin"));
        add(new Log("Création de la table comments", "1", "admin"));
    }
    /**
     * Cette méthode crée la table log dans la base de données si elle n'existe pas
     */
    private void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS log ("
                + "id_log SERIAL PRIMARY KEY,"
                + "date TIMESTAMP NOT NULL,"
                + "action VARCHAR(255) NOT NULL,"
                + "id_user VARCHAR(255) NOT NULL,"
                + "name_user VARCHAR(255) NOT NULL"
                + ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }

    private void setLog(Log log, PreparedStatement ps) throws SQLException {
        // Convertir java.util.Date en java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(log.getDate().getTime());
        ps.setDate(1, sqlDate);
        ps.setString(2, log.getAction());
        ps.setString(3, log.getIdUser());
        ps.setString(4, log.getNameUser());
    }


    /**
     * Cette méthode ajoute un log dans la base de données
     * @param log le log à ajouter
     */
    @Override
    public void add(Log log) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO log (date,action,id_user,name_user) VALUES (?, ?, ?, ?)")) {
            setLog(log, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du log : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode supprime un log de la base de données
     * @param logId l'id du log à supprimer
     */
    @Override
    public void delete(Integer logId) throws RuntimeException{
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM log WHERE id_log = ?")) {
            ps.setInt(1, logId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du log : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode met à jour un log dans la base de données
     * @param log le log à mettre à jour
     */
    @Override
    public void update(Log log) throws RuntimeException{
        try (PreparedStatement ps = connection.prepareStatement("UPDATE log SET date = ?, action = ?, id_user = ?, name_user = ? WHERE id_log = ?")) {
            setLog(log, ps);
            ps.setInt(5, log.getIdLog());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du log : " + e.getMessage(), e);
        }
    }

/**
     * Cette méthode récupère un log dans la base de données
     * @param logId l'id du log à récupérer
     * @return le log récupéré
     */
    @Override
    public Log get(Integer logId) {
        String sql = "SELECT * FROM log WHERE id_log = ?";
        Log log = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, logId); // Définir l'identifiant de log
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    log = new Log();
                    // Assurez-vous que les noms des colonnes correspondent à votre table de base de données
                    log.setDate(rs.getDate("date"));
                    log.setAction(rs.getString("action"));
                    log.setIdUser(rs.getString("id_user"));
                    log.setNameUser(rs.getString("name_user"));
                    log.setIdLog(rs.getInt("id_log")); // Supposons que vous avez un champ id_log dans votre table
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du log : " + e.getMessage(), e);
        }

        return log;
    }


    /**
     * Cette methode permet de récuper tous les logs de la base de données dans l'ordre chronologique
     * @return un tableau de logs
    */
    public Log[] getAll() {
        List<Log> logs = new ArrayList<>();
        String sql = "SELECT * FROM log";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Log log = new Log();
                // Assurez-vous que les noms des colonnes correspondent à votre table de base de données
                log.setDate(rs.getDate("date"));
                log.setAction(rs.getString("action"));
                log.setIdUser(rs.getString("id_user"));
                log.setNameUser(rs.getString("name_user"));
                log.setIdLog(rs.getInt("id_log")); // Supposons que vous avez un champ id_log dans votre table

                logs.add(log);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des logs : " + e.getMessage(), e);
        }

        return logs.toArray(new Log[0]);
    }

}
