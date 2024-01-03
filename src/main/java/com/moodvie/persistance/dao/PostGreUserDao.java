package com.moodvie.persistance.dao;
import com.moodvie.persistance.model.User;
import com.moodvie.persistance.database.DatabaseConnection;

import java.sql.*;


/**
 * PostGreUserDao est une implémentation de UserDao spécifique à PostGre
 * Permet de gérer les utilisateurs
 */
public class PostGreUserDao extends UserDao{
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    public PostGreUserDao() {
        dropTable();
        createTable();
    }

    /**
     * Cette méthode supprime la table users de la base de données
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }

    /**
     * Cette méthode crée la table users dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "pseudo VARCHAR(100), " +
                "firstName VARCHAR(100), " +
                "lastName VARCHAR(100), " +
                "birthDate VARCHAR(100), " +
                "email VARCHAR(100)," +
                "password VARCHAR(100)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }


    /**
     * Cette méthode ajoute un utilisateur dans la base de données
     * @param user l'utilisateur à ajouter
     */
    @Override
    public void add(User user) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO users (pseudo,firstName,lastName,birthDate,email,password) VALUES (?, ?, ?, ?, ?, ?)")) {
            setUser(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode permet de définir les paramètres d'un utilisateur dans une requête SQL
     * @param user l'utilisateur à ajouter
     * @param ps la requête SQL à modifier
     */
    private void setUser(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getPseudo());
        ps.setString(2, user.getFirstname());
        ps.setString(3, user.getLastname());
        ps.setString(4, user.getBirthday());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPassword());
    }

    /**
     * Cette méthode récupère un utilisateur dans la base de données
     * @param email l'email de l'utilisateur à récupérer
     *              @return l'utilisateur récupéré
     */
    public User get(String email) throws RuntimeException{
        // code pour récupérer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            User user = get(ps);
            if (user != null) return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Cette méthode récupère un utilisateur dans la base de données
     * @param ps la requête SQL à exécuter
     *              @return l'utilisateur récupéré
     */
    private User get(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setPseudo(rs.getString("pseudo"));
            user.setFirstname(rs.getString("firstName"));
            user.setLastname(rs.getString("lastName"));
            user.setBirthday(rs.getString("birthDate"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
        return null;
    }

    /**
     * Cette méthode met à jour un utilisateur dans la base de données
     * @param user l'utilisateur à mettre à jour
     */
    @Override
    public void update(User user) throws RuntimeException{
        // code pour mettre à jour un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET pseudo = ?, firstName = ?, lastName = ?, birthDate = ?, email = ?, password = ? WHERE id = ?");
            setUser(user, ps);
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode récupère un utilisateur dans la base de données
     * @param userId l'identifiant de l'utilisateur à récupérer
     * @return l'utilisateur récupéré
     */
    public User get(Integer userId) throws RuntimeException{
        // code pour récupérer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, userId);
            User user = get(ps);
            if (user != null) return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Cette méthode supprime un utilisateur de la base de données
     * @param userId l'identifiant de l'utilisateur à supprimer
     */
    public void delete(Integer userId) throws RuntimeException{
        // code pour supprimer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
