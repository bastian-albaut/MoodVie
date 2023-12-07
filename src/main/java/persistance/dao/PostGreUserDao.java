package persistance.dao;

import java.sql.*;

public class PostGreUserDao extends UserDao{
    private DatabaseConnection  dbConnection= new DatabaseConnection();
    Connection connection = dbConnection.getConnection();


    @Override
    public void addUser(User user) {
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO users (id, name, email) VALUES (?, ?, ?)")) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String id) {
        // code pour récupérer un utilisateur dans une base de données SQL
        return null;
    }
    @Override
    public void updateUser(User user) {
        // code pour mettre à jour un utilisateur dans une base de données SQL
    }

    public User getUser(int userId) {
        // code pour récupérer un utilisateur dans une base de données SQL
        return null;
    }

    public void deleteUser(int userId) {
        // code pour supprimer un utilisateur dans une base de données SQL
    }
}
