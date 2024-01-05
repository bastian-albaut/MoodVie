package com.moodvie.persistance.dao;

import com.moodvie.persistance.database.DatabaseConnection;
import com.moodvie.persistance.model.Log;
import com.moodvie.persistance.model.WatchLater;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGreWatchLaterDao extends WatchLaterDao{

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreWatchLaterDao() {
        //dropTable();
        createTable();
    }

    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS watchLater";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }

    private void setWatchLater(WatchLater watchLater, PreparedStatement ps) throws SQLException {
        ps.setString(1, watchLater.getIdFilm());
        ps.setInt(2, watchLater.getIdUser());
    }

    /**
     * Cette méthode crée la table watchLater dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS watchLater (" +
                "id SERIAL PRIMARY KEY, " +
                "id_film VARCHAR(100), " + // Ajout d'une virgule ici
                "id_user INTEGER, " + // Ajout d'une virgule ici
                "FOREIGN KEY (id_user) REFERENCES users(id)" + // Exemple de clé étrangère pour id_user
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }

    /**
     * Cette methode permet de récuper tous les id des films de la table watchLater
     * @return une liste d'id de film
     */
    public List<WatchLater> getAll(int userId) {
        List<WatchLater> watchLaterList = new ArrayList<>();
        String sql = "SELECT * FROM watchLater WHERE id_user = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                WatchLater watchLater = new WatchLater();
                watchLater.setId(rs.getInt("id"));
                watchLater.setIdFilm(rs.getString("id_film"));
                watchLater.setIdUser(rs.getInt("id_user"));
                watchLaterList.add(watchLater);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }

        return watchLaterList;
    }

    public void add(WatchLater watchLater){
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO watchLater (id_film, id_user) VALUES (?, ?)")) {
            setWatchLater(watchLater, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage(), e);
        }
    }


    public WatchLater get(Integer watchLaterId){
        WatchLater watchLater = new WatchLater();
        String sql = "SELECT * FROM watchLater WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, watchLaterId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                watchLater.setId(rs.getInt("id"));
                watchLater.setIdFilm(rs.getString("id_film"));
                watchLater.setIdUser(rs.getInt("id_user"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }
        return watchLater;
    }

    public WatchLater get(String idFilm, int idUser){
        WatchLater watchLater = new WatchLater();
        String sql = "SELECT * FROM watchLater WHERE id_film = ? AND id_user = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idFilm);
            pstmt.setInt(2, idUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                watchLater.setId(rs.getInt("id"));
                watchLater.setIdFilm(rs.getString("id_film"));
                watchLater.setIdUser(rs.getInt("id_user"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }
        System.out.println(watchLater.getIdFilm());
        return watchLater;
    }
    public void update(WatchLater watchLater){

    }
    public void delete(Integer watchLaterId){
        String sql = "DELETE FROM watchLater WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, watchLaterId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur : " + e.getMessage(), e);
        }

    }
}
