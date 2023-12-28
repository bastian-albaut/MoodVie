package com.moodvie.persistance.factory;

import com.moodvie.persistance.dao.PostGreSubscribeDao;
import com.moodvie.persistance.dao.PostGreTypeOfSubscribeDao;
import com.moodvie.persistance.dao.PostGreUserDao;
import com.moodvie.persistance.dao.SubscribeDao;
import com.moodvie.persistance.dao.TypeOfSubscribeDao;
import com.moodvie.persistance.dao.UserDao;

/**
 * PostGreFactory est une implémentation de AbstractDaoFactory
 * Permet de gérer les DAO
 */

public class PostGreFactory extends AbstractDaoFactory {
    UserDao userDao;
    SubscribeDao subscribeDao;
    TypeOfSubscribeDao typeOfSubscribeDao;

    @Override
    public UserDao getUserDao() {
        // retourne une implémentation de persistance.dao.UserDao spécifique à SQL
        if (userDao == null) {
            userDao = new PostGreUserDao();
        }
        return userDao;
    }
    
    @Override
    public SubscribeDao getSubscribeDao() {
        // retourne une implémentation de persistance.dao.SubscribeDao spécifique à SQL
        if (subscribeDao == null) {
            subscribeDao = new PostGreSubscribeDao();
        }
        return subscribeDao;
    }

    @Override
    public TypeOfSubscribeDao getTypeOfSubscribeDao() {
        // retourne une implémentation de persistance.dao.TypeOfSubscribeDao spécifique à SQL
        if(typeOfSubscribeDao == null) {
            typeOfSubscribeDao = new PostGreTypeOfSubscribeDao();
        }
        return typeOfSubscribeDao;
    }
}
