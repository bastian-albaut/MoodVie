package business;

import model.User;
import persistance.factory.AbstractDaoFactory;

// Singleton pattern
public class UserFacade {
    private static UserFacade instance;
    private AbstractDaoFactory abstractDaoFactory;

    private UserFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }  

    /**
     * Cette méthode permet de connecter un utilisateur
     * @param email l'email de l'utilisateur à connecter
     * @param password le mot de passe de l'utilisateur à connecter
     * @return l'utilisateur connecté
     */
    public User login(String email, String password) {
        // Vérification des paramètres
        if (email == null || password == null) {
            return null;
        }

        return abstractDaoFactory.getUserDao().login(email, password);
    }
}
