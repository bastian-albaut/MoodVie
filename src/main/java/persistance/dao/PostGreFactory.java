package persistance.dao;

public class PostGreFactory implements DaoFactory {
    @Override
    public UserDao getUserDao() {
        // retourne une implémentation de persistance.dao.UserDao spécifique à SQL
        return new PostGreUserDao();
    }
    // implémentations pour d'autres DAO si nécessaire
}
