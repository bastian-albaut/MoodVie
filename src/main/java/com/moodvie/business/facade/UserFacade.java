package com.moodvie.model.facade;

import com.moodvie.model.persistance.dao.UserDao;
import com.moodvie.model.persistance.factory.AbstractDaoFactory;
import com.moodvie.model.User;

/**
 * UserFacade est un singleton
 * Permet de gérer les utilisateurs
 */
public class UserFacade {
    private static UserFacade instance;
    private AbstractDaoFactory abstractDaoFactory;
    private User user;

    private UserFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    public static synchronized UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }  

    /**
     * Cette méthode permet de créer un utilisateur
     * @param name nom de l'utilisateur
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return true si l'utilisateur a été créé, false sinon
     */
    public Boolean register(String name, String email, String password) {
        UserDao UserDao = abstractDaoFactory.getUserDao();

        // Vérifie si un utilisateur existe déjà pour cet email
        if (UserDao.getUser(email) != null) {
            return false;
        }

        User user = new User(name,email,password);

        try{
            UserDao.addUser(user);
        } catch(Exception e){
            System.out.println(e);
        }

        this.user = user;
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

        User user = UserDao.getUser(email);
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
        return user;
    }

    /**
     * Cette méthode permet de déconnecter l'utilisateur courant
     * @return true si l'utilisateur a été déconnecté
     */
    public Boolean logout(){
        this.user = null;
        return true;
    }

    /**
     * Cette méthode permet de supprimer l'utilisateur courant
     * @return true si l'utilisateur a été supprimé, false sinon
     */
    public Boolean DeleteUser(){
        UserDao UserDao = abstractDaoFactory.getUserDao();

        try {
            UserDao.deleteUser(this.user.getId());
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
}
