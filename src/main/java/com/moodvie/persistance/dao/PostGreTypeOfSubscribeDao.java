package com.moodvie.persistance.dao;
import com.moodvie.persistance.model.TypeOfSubscribe;
import com.moodvie.persistance.database.DatabaseConnection;

import java.sql.*;


/**
 * PostGreTypeOfSubscribeDao est une implémentation de SubscribeDao spécifique à PostGre
 * Permet de gérer les types abonnements
 */
public class PostGreTypeOfSubscribeDao extends TypeOfSubscribeDao {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public PostGreTypeOfSubscribeDao() {
        dropTable();
        createTable();
    }

    /**
     * Cette méthode supprime la table typeOfSubscribes de la base de données et toutes les tables qui en dépendent
     */
    private void dropTable() {
        String sql = "DROP TABLE IF EXISTS typeOfSubscribes CASCADE";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la table : " + e.getMessage(), e);
        }
    }
    /**
     * Cette méthode crée la table typeOfSubscribes dans la base de données si elle n'existe pas
     */
    void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS typeOfSubscribes (" +
                "id SERIAL PRIMARY KEY, " +
                "label VARCHAR(100), " +
                "price DECIMAL(10,2), " +
                "numberOfDays INTEGER, " +
                "features VARCHAR(100)[] " +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de la table : " + e.getMessage(), e);
        }
    }


    /**
     * Cette méthode ajoute un type d'abonnement dans la base de données
     * @param typeOfSubscribe le type d'abonnement à ajouter
     */
    @Override
    public void addTypeOfSubscribe(TypeOfSubscribe typeOfSubscribe) throws RuntimeException{
        try (PreparedStatement ps =  connection.prepareStatement("INSERT INTO typeOfSubscribes (id, label, price, numberOfDays, features) VALUES (?, ?, ?, ?, ?)")) {
            setTypeOfSubscribe(typeOfSubscribe, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode permet de définir les paramètres d'un type d'abonnement dans une requête SQL
     * @param typeOfSubscribe le type d'abonnement à ajouter
     * @param ps la requête SQL à modifier
     */
    private void setTypeOfSubscribe(TypeOfSubscribe typeOfSubscribe, PreparedStatement ps) throws SQLException {
        ps.setInt(1, typeOfSubscribe.getId());
        ps.setString(2, typeOfSubscribe.getLabel());
        ps.setDouble(3, typeOfSubscribe.getPrice());
        ps.setInt(4, typeOfSubscribe.getNumberOfDays());
        ps.setArray(5, connection.createArrayOf("VARCHAR", typeOfSubscribe.getFeatures().toArray()));
    }
    
    /**
     * Cette méthode met à jour un type d'abonnement dans la base de données
     * @param typeOfSubscribe le type d'abonnement à mettre à jour
     */
    @Override
    public void updateTypeOfSubscribe(TypeOfSubscribe typeOfSubscribe) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE typeOfSubscribes SET id = ?, label = ?, price = ?, numberOfDays = ?, features = ? WHERE id = ?");
            setTypeOfSubscribe(typeOfSubscribe, ps);
            ps.setInt(8, typeOfSubscribe.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode récupère un type d'abonnement dans la base de données
     * @param ps la requête SQL à exécuter
     * @return le type d'abonnement récupéré
     */
    private TypeOfSubscribe getTypeOfSubscribe(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            TypeOfSubscribe typeOfSubscribe = new TypeOfSubscribe();
            typeOfSubscribe.setId(rs.getInt("id"));
            typeOfSubscribe.setLabel(rs.getString("label"));
            typeOfSubscribe.setPrice(rs.getDouble("price"));
            typeOfSubscribe.setNumberOfDays(rs.getInt("numberOfDays"));
            typeOfSubscribe.setFeatures((String[]) rs.getArray("features").getArray());

            return typeOfSubscribe;
        }
        return null;
    }
    
    /**
     * Cette méthode récupère un type d'abonnement dans la base de données
     * @param typeOfSubscribeId l'identifiant de le type d'abonnement à récupérer
     * @return le type d'abonnement récupéré
     */
    public TypeOfSubscribe getTypeOfSubscribe(int typeOfSubscribeId) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM typeOfSubscribes WHERE id = ?");
            ps.setInt(1, typeOfSubscribeId);
            TypeOfSubscribe typeOfSubscribe = getTypeOfSubscribe(ps);
            if (typeOfSubscribe != null) return typeOfSubscribe;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Cette méthode supprime un type d'abonnement de la base de données
     * @param typeOfSubscribeId l'identifiant de le type d'abonnement à supprimer
     */
    public void deleteTypeOfSubscribe(int typeOfSubscribeId) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM typeOfSubscribes WHERE id = ?");
            ps.setInt(1, typeOfSubscribeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
