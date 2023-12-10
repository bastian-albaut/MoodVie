package persistance.dao;
import model.User;
import persistance.database.DatabaseConnection;

import java.sql.*;


/**
 * PostGreUserDao est une implémentation de UserDao spécifique à PostGre
 * Permet de gérer les utilisateurs
 */
public class PostGreUserDao extends UserDao{
    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    public PostGreUserDao() {
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
                "name VARCHAR(100), " +
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
    public void addUser(User user) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO users (name, email,password) VALUES (?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode récupère un utilisateur dans la base de données
     * @param email l'email de l'utilisateur à récupérer
     *              @return l'utilisateur récupéré
     */
    public User getUser(String email) throws RuntimeException{
        // code pour récupérer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, email);
            User user = getUser(ps);
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
    private User getUser(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
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
    public void updateUser(User user) throws RuntimeException {
        // code pour mettre à jour un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode récupère un utilisateur dans la base de données
     * @param userId l'identifiant de l'utilisateur à récupérer
     *               @return l'utilisateur récupéré
     */
    public User getUser(int userId) throws RuntimeException{
        // code pour récupérer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, userId);
            User user = getUser(ps);
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
    public void deleteUser(int userId) throws RuntimeException{
        // code pour supprimer un utilisateur dans une base de données SQL
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode permet de connecter un utilisateur
     * @param email l'email de l'utilisateur à connecter
     * @param password le mot de passe de l'utilisateur à connecter
     * @return l'utilisateur connecté
     */
    public User login(String email, String password) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            User user = getUser(ps);
            if (user != null) return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
