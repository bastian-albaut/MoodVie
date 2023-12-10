package business;

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
}
