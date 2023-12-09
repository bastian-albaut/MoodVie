package persistance.dao;
/**
 * DaoFactory est un singleton
 * Permet de gérer les DAO
 */

public abstract class AbstractDaoFactory {
    private static AbstractDaoFactory instance;
    protected AbstractDaoFactory(){}

    /**
     * Permet de récupérer une instance de DaoFactory
     * @return une instance de DaoFactory
     */
    public static AbstractDaoFactory getFactory() {
        if (instance == null) {
            instance = new PostGreFactory();
        }
        return instance;
    }

    /**
     * Permet de récupérer une instance de UserDao
     * @return une instance de UserDao
     */
    abstract public UserDao getUserDao();
    // autres méthodes pour différents types de DAO
}
