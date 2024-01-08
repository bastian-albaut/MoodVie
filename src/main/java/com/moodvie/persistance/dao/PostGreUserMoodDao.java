package com.moodvie.persistance.dao;

import com.moodvie.persistance.database.DatabaseConnection;
import com.moodvie.persistance.model.UserMood;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGreUserMoodDao extends UserMoodDao {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreUserMoodDao() {
        dropTableUserMood();
        dropTableUserMoodFilm();
        createTable();
    }

private void dropTableUserMood() {
    String sql = "DROP TABLE IF EXISTS userMood CASCADE";
    try (Statement stmt = connection.createStatement()) {
        stmt.execute(sql);
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression de la table userMood : " + e.getMessage(), e);
    }
}

private void dropTableUserMoodFilm() {
    String sql = "DROP TABLE IF EXISTS userMoodFilms";
    try (Statement stmt = connection.createStatement()) {
        stmt.execute(sql);
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression de la table userMoodFilms : " + e.getMessage(), e);
    }
}

   
    private void createTable() {
        String sqlUserMood = "CREATE TABLE IF NOT EXISTS userMood ("
                + "mood_id SERIAL PRIMARY KEY,"
                + "mood_description VARCHAR(255) NOT NULL,"
                + "user_id INT NOT NULL"
                + ");";

        String sqlUserMoodFilms = "CREATE TABLE IF NOT EXISTS userMoodFilms ("
                + "mood_id INT NOT NULL,"
                + "film_id VARCHAR(255) NOT NULL,"
                + "FOREIGN KEY (mood_id) REFERENCES userMood(mood_id)"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlUserMood);
            stmt.execute(sqlUserMoodFilms);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création des tables userMood et userMoodFilms : " + e.getMessage(), e);
        }
    }

    // ... autres méthodes ...

    @Override
    public void add(UserMood userMood) {
        String sqlInsertMood = "INSERT INTO userMood (mood_description, user_id) VALUES (?, ?) RETURNING mood_id;";
        try (PreparedStatement ps = connection.prepareStatement(sqlInsertMood, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userMood.getMoodDescription());
            ps.setInt(2, userMood.getUserId());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La création de l'humeur a échoué, aucune ligne affectée.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userMood.setMoodID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La création de l'humeur a échoué, aucun ID obtenu.");
                }
            }

            // Ajouter les films associés à la table de jointure
            if (userMood.getAssociatedFilmIDs() != null) {
                String sqlInsertFilm = "INSERT INTO userMoodFilms (mood_id, film_id) VALUES (?, ?);";
                try (PreparedStatement psFilm = connection.prepareStatement(sqlInsertFilm)) {
                    for (String filmId : userMood.getAssociatedFilmIDs()) {
                        psFilm.setInt(1, userMood.getMoodID());
                        psFilm.setString(2, filmId);
                        psFilm.addBatch();
                    }
                    psFilm.executeBatch();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de UserMood : " + e.getMessage(), e);
        }
    }


    /**
     * Cette méthode récupère un UserMood dans la base de données
     * @param moodId l'ID du UserMood à récupérer
     * @return le UserMood récupéré
     */
    // ...

    @Override
    public UserMood get(Integer moodId) {
        String sql = "SELECT mood_id, mood_description FROM userMood WHERE mood_id = ?";
        String sqlFilms = "SELECT film_id FROM userMoodFilms WHERE mood_id = ?";
        UserMood userMood = null;
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, moodId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userMood = new UserMood();
                    userMood.setMoodID(rs.getInt("mood_id"));
                    userMood.setMoodDescription(rs.getString("mood_description"));
                    
                    try (PreparedStatement psFilms = connection.prepareStatement(sqlFilms)) {
                        psFilms.setInt(1, moodId);
                        try (ResultSet rsFilms = psFilms.executeQuery()) {
                            List<String> filmIDs = new ArrayList<>();
                            while (rsFilms.next()) {
                                filmIDs.add(rsFilms.getString("film_id"));
                            }
                            userMood.setAssociatedFilmIDs(filmIDs);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de UserMood : " + e.getMessage(), e);
        }
    
        return userMood;
    }
    

@Override
public void update(UserMood userMood) {
    String sqlUpdateMood = "UPDATE userMood SET mood_description = ? WHERE mood_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sqlUpdateMood)) {
        ps.setString(1, userMood.getMoodDescription());
        ps.setInt(2, userMood.getMoodID());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la mise à jour de UserMood : " + e.getMessage(), e);
    }

    // Mettre à jour les films associés dans la table de jointure
    // Supprimez d'abord tous les films associés existants
    String sqlDeleteFilms = "DELETE FROM userMoodFilms WHERE mood_id = ?";
    try (PreparedStatement psDelete = connection.prepareStatement(sqlDeleteFilms)) {
        psDelete.setInt(1, userMood.getMoodID());
        psDelete.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression des films associés à UserMood : " + e.getMessage(), e);
    }

    // Ajoutez ensuite les nouveaux films associés
    String sqlInsertFilm = "INSERT INTO userMoodFilms (mood_id, film_id) VALUES (?, ?);";
    try (PreparedStatement psFilm = connection.prepareStatement(sqlInsertFilm)) {
        for (String filmId : userMood.getAssociatedFilmIDs()) {
            psFilm.setInt(1, userMood.getMoodID());
            psFilm.setString(2, filmId);
            psFilm.addBatch();
        }
        psFilm.executeBatch();
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de l'ajout de films associés à UserMood : " + e.getMessage(), e);
    }
}

@Override
public void delete(Integer moodId) {
    // Supprimez d'abord les films associés dans la table de jointure
    String sqlDeleteFilms = "DELETE FROM userMoodFilms WHERE mood_id = ?";
    try (PreparedStatement psDelete = connection.prepareStatement(sqlDeleteFilms)) {
        psDelete.setInt(1, moodId);
        psDelete.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression des films associés à UserMood : " + e.getMessage(), e);
    }

    // Ensuite, supprimez le mood lui-même
    String sqlDeleteMood = "DELETE FROM userMood WHERE mood_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sqlDeleteMood)) {
        ps.setInt(1, moodId);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression de UserMood : " + e.getMessage(), e);
    }
}

// ...

@Override
public UserMood[] getAll() {
    List<UserMood> userMoods = new ArrayList<>();
    String sql = "SELECT * FROM userMood";
    String sqlFilms = "SELECT film_id FROM userMoodFilms WHERE mood_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            UserMood userMood = new UserMood();
            userMood.setMoodID(rs.getInt("mood_id"));
            userMood.setMoodDescription(rs.getString("mood_description"));

            try (PreparedStatement psFilms = connection.prepareStatement(sqlFilms)) {
                psFilms.setInt(1, userMood.getMoodID());
                try (ResultSet rsFilms = psFilms.executeQuery()) {
                    List<String> filmIDs = new ArrayList<>();
                    while (rsFilms.next()) {
                        filmIDs.add(rsFilms.getString("film_id"));
                    }
                    userMood.setAssociatedFilmIDs(filmIDs);
                }
            }

            userMoods.add(userMood);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la récupération des UserMoods : " + e.getMessage(), e);
    }

    return userMoods.toArray(new UserMood[0]);
}

@Override
public List<UserMood> searchMood(String description) {
    List<UserMood> userMoods = new ArrayList<>();
    String sql = "SELECT * FROM userMood WHERE mood_description LIKE ?";
    String sqlFilms = "SELECT film_id FROM userMoodFilms WHERE mood_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, "%" + description + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UserMood userMood = new UserMood();
                userMood.setMoodID(rs.getInt("mood_id"));
                userMood.setMoodDescription(rs.getString("mood_description"));

                try (PreparedStatement psFilms = connection.prepareStatement(sqlFilms)) {
                    psFilms.setInt(1, userMood.getMoodID());
                    try (ResultSet rsFilms = psFilms.executeQuery()) {
                        List<String> filmIDs = new ArrayList<>();
                        while (rsFilms.next()) {
                            filmIDs.add(rsFilms.getString("film_id"));
                        }
                        userMood.setAssociatedFilmIDs(filmIDs);
                    }
                }

                userMoods.add(userMood);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la recherche de UserMoods : " + e.getMessage(), e);
    }

    return userMoods;
}




}