package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Rating;
import com.moodvie.persistance.database.DatabaseConnection;
import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

/**
 * PostGreRatingDao est une implémentation de RatingDao spécifique à PostGre
 * Permet de gérer les notes
 */
public class PostGreRatingDao extends RatingDao {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreRatingDao() {
        dropTable();
        createTable();
    }

    /**
     * Cette méthode supprime la table ratings de la base de données et toutes les tables qui en dépendent
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS ratings CASCADE";
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
                "userId INTEGER, " +
                "filmId VARCHAR(100)," +
                "value INTEGER, " +
                "comment VARCHAR(100), " +
                "FOREIGN KEY (userId) REFERENCES users(id)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }


     /**
     * Cette méthode permet de définir les paramètres d'une note dans une requête SQL
     * @param rating la note à ajouter
     * @param ps la requête SQL à modifier
     */
    private static final AtomicInteger idGenerator = new AtomicInteger();

    private void setRating(Rating rating, PreparedStatement ps) throws SQLException {
        int nextId = idGenerator.incrementAndGet();
        ps.setInt(1, nextId);
        ps.setInt(2, rating.getIdUser());
        ps.setString(3, rating.getIdFilm());
        ps.setInt(4, rating.getValue());
        ps.setString(5, rating.getComment());
    }   

    /**
     * Cette méthode permet d'ajouter une note à la base de données
     * @param rating la note à ajouter
     */
    @Override 
    public void addRating(Rating rating) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO ratings (id, userId, filmId, value, comment) VALUES (?, ?, ?, ?, ?)")) {
            setRating(rating, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la note : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode récupère une note dans la base de données
     * @param idRating l'identifiant de la note à récupérer
     * @return la note récupéré
     */
    public Rating getRating(int idRating) {
        Rating rating = new Rating();
        String sql = "SELECT * FROM ratings WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idRating);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rating.setIdRating(rs.getInt("id"));
                rating.setIdUser(rs.getInt("userId"));
                rating.setIdFilm(rs.getString("filmId"));
                rating.setValue(rs.getInt("value"));
                rating.setComment(rs.getString("comment"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }

        return rating;
    }

    public Rating getRating(int idUser, String idFilm) {

        String sql = "SELECT * FROM ratings WHERE userId = ? AND filmId = ?";
        Rating rating = null; 

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setString(2, idFilm);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rating = new Rating();
                rating.setIdRating(rs.getInt("id"));
                rating.setIdUser(rs.getInt("userId"));
                rating.setIdFilm(rs.getString("filmId"));
                rating.setValue(rs.getInt("value"));
                rating.setComment(rs.getString("comment"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }

        return rating;
    }

    /**
     * Cette méthode récupère une note dans la base de données
     * @param ps la requête SQL à exécuter
     * @return la note récupéré
     */
    public Rating getRating(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Rating rating = new Rating();
            rating.setIdRating(rs.getInt("id"));
            rating.setIdUser(rs.getInt("userId"));
            rating.setIdFilm(rs.getString("filmId"));
            rating.setValue(rs.getInt("value"));
            rating.setComment(rs.getString("comment"));
            return rating;
        }
        return null;
    }

     /**
     * Cette méthode met à jour une note dans la base de données
     * @param rating la note à mettre à jour
     */
    @Override
    public void update(Rating rating) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ratings SET userId = ?, filmId = ?, value = ?, comment = ? WHERE id = ?");
            setRating(rating, ps);
            ps.setInt(5, rating.getIdRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateComment(Rating rating) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE ratings SET comment = ? WHERE id = ?");
            ps.setString(1, rating.getComment());
            ps.setInt(2, rating.getIdRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     /**
     * Cette méthode supprime une note de la base de données
     * @param idRating l'identifiant de la note à supprimer
     */
    public void delete(int idRating) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ratings WHERE id = ?");
            ps.setInt(1, idRating);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //chatgpt
    /**
     * Cette méthode permet de récupérer la note moyenne d'un film
     * @param idFilm l'id du film
     * @return la note moyenne du film
     */
    public double getAverageRating(String idFilm) {
        double averageRating = 0;
        String sql = "SELECT AVG(value) FROM ratings WHERE filmId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idFilm);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                averageRating = rs.getDouble("avg");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }

        return averageRating;
    }


    /**
     * Cette méthode permet de récupérer les commentaires d'un film
     * @param idFilm l'id du film
     * @return la liste des commentaires du film
     */
    public String getComments(String idFilm) {
        String comments = "";
        String sql = "SELECT comment FROM ratings WHERE filmId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idFilm);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                comments += rs.getString("comment") + "\n";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des données : " + e.getMessage(), e);
        }

        return comments;
    }


}
