package com.moodvie.business.facade;

import com.moodvie.persistance.dao.UserDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.User;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * UserFacade est un singleton
 * Permet de gérer les utilisateurs
 */
public class UserFacade implements Observable {

    private final List<InvalidationListener> listeners = new ArrayList<>();
    private static UserFacade instance;
    private final AbstractDaoFactory abstractDaoFactory;
    private User user;


    private UserFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
        this.login("k", "k");
    }

    public static synchronized UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }  

    /**
     * Cette méthode permet de créer un utilisateur
     * 
     * @param pseudo pseudo de l'utilisateur
     * @param firstname prénom de l'utilisateur
     * @param lastname nom de l'utilisateur
     * @param birthday date de naissance de l'utilisateur
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * 
     * @return true si l'utilisateur a été créé, false sinon
     */
    public Boolean register(String pseudo,String firstname,String lastname,String birthday, String email, String password) {
        UserDao UserDao = abstractDaoFactory.getUserDao();

        // Vérifie si un utilisateur existe déjà pour cet email
        if (UserDao.get(email) != null) {
            return false;
        }

        User user = new User(pseudo,firstname,lastname,birthday,email,password);
        
        try{
            UserDao.add(user);
        } catch(Exception e){
            System.out.println(e);
        }

        // Get the user created
        User userCreated = UserDao.get(email);
        System.out.println("userCreated: " + userCreated);
        
        // Create a subscription for the user created
        boolean subscriptionCreated = createSubscriptionForUser(userCreated.getId());

        // Delete the user created if the subscription creation failed
        if (!subscriptionCreated) {
            System.out.println("Failed to create subscription for userId: " + userCreated.getId());
            UserDao.delete(userCreated.getId());
            return false;
        }
        
        System.out.println("Subscription created for userId: " + userCreated.getId());

        this.user = userCreated;
        this.notifyListeners(); // Notifie les observateurs après l'inscription
        return true;
    }

    /**
     * Crée automatiquement un abonnement pour un utilisateur donné.
     *
     * @param userId id de l'utilisateur pour lequel créer l'abonnement
     * 
     * @return true si l'abonnement a été créé, false sinon
     */
    private boolean createSubscriptionForUser(int userId) {
        SubscribeFacade subscribeFacade = SubscribeFacade.getInstance();
        Timestamp startDate = new Timestamp(System.currentTimeMillis());
        boolean isActive = true;
        int typeOfSubscribeId = 1; // The ID of the basic subscription

        boolean subscriptionCreated = subscribeFacade.createSubscribe(startDate, isActive, typeOfSubscribeId, userId);

        if (!subscriptionCreated) {
            System.out.println("Failed to create subscription for userId: " + userId);
            return false;
        }

        return true;
    }

    /**
     * Cette méthode permet de connecter un utilisateur
     * @param email l'email de l'utilisateur à connecter
     * @param password le mot de passe de l'utilisateur à connecter
     * @return l'utilisateur connecté
     */
    public User login(String email, String password) {
        UserDao UserDao = abstractDaoFactory.getUserDao();
        
        // Vérification des paramètres
        if (email == null || password == null) {
            return null;
        }

        User user = UserDao.get(email);
        System.out.println("user : " + user);

        // Verifie si l'utilisateur existe
        if(user == null){
            return null;
        }

        // Verifie si le mot de passe est correct
        if (!user.getPassword().equals(password)) {
            return null;
        }

        this.user = user;
        this.notifyListeners();
        return user;
    }

    /**
     * Cette méthode permet de déconnecter l'utilisateur courant
     * @return true si l'utilisateur a été déconnecté
     */
    public Boolean logout(){
        this.user = null;
        this.notifyListeners();
        return true;
    }

    /**
     * Cette méthode permet de supprimer l'utilisateur courant
     * @return true si l'utilisateur a été supprimé, false sinon
     */
    public Boolean deleteUser(){
        UserDao UserDao = abstractDaoFactory.getUserDao();

        try {
            UserDao.delete(this.user.getId());
        } catch(Exception e){
            System.out.println(e);
            return false;
        }

        this.user = null;
        return true;
    }

    /**
     * Cette méthode permet de récupérer l'utilisateur courant
     * @return l'utilisateur courant ou null si aucun utilisateur n'est connecté
     */
    public User getUser(){
        if(this.user == null){
            return null;
        }

        return this.user;
    }

    /**
     * Cette méthode permet de mettre à jour l'utilisateur courant
     * @param pseudo pseudo de l'utilisateur
     * @param firstname prénom de l'utilisateur
     * @param lastname nom de l'utilisateur
     * @param birthday date de naissance de l'utilisateur
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return true si l'utilisateur a été mis à jour, false sinon
     */
    public User updateUser(String pseudo,String firstname,String lastname,String birthday, String email, String password) {
        UserDao UserDao = abstractDaoFactory.getUserDao();

        // Vérifie si un utilisateur existe déjà pour cet email
        if (UserDao.get(email) != null) {
            return null;
        }

        User user = new User(pseudo, firstname, lastname, birthday, email, password);

        try {
            UserDao.update(user);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.user = user;
        this.notifyListeners(); // Notifie les observateurs après l'inscription
        return user;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        listeners.add(invalidationListener);

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        listeners.remove(invalidationListener);
    }
    private void notifyListeners() {
        for (InvalidationListener listener : listeners) {
            System.out.println("notifyListeners");
            listener.invalidated(this);
        }
    }
}
