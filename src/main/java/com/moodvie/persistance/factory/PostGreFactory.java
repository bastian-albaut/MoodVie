package com.moodvie.persistance.factory;

import com.moodvie.persistance.dao.PostGreUserDao;
import com.moodvie.persistance.dao.UserDao;

/**
 * PostGreFactory est une implémentation de AbstractDaoFactory
 * Permet de gérer les DAO
 */

public class PostGreFactory extends AbstractDaoFactory {
    UserDao userDao;

    @Override
    public UserDao getUserDao() {
        // retourne une implémentation de persistance.dao.UserDao spécifique à SQL
        if (userDao == null) {
            userDao = new PostGreUserDao();
        }
        return userDao;
    }
    // implémentations pour d'autres DAO si nécessaire
}
