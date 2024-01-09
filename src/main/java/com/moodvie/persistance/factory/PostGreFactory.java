package com.moodvie.persistance.factory;

import com.moodvie.persistance.dao.*;

/**
 * PostGreFactory est une implémentation de AbstractDaoFactory
 * Permet de gérer les DAO
 */

public class PostGreFactory extends AbstractDaoFactory {
    UserDao userDao;
    SubscribeDao subscribeDao;
    TypeOfSubscribeDao typeOfSubscribeDao;

    LogDao logDao;

    FilmDao filmDao;

    WatchLaterDao watchLaterDao;


  
    RatingDao ratingDao;

  
    UserMoodDao userMoodDao;

  

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
    public LogDao getLogDao() {
        // retourne une implémentation de persistance.dao.LogDao spécifique à SQL
        if (logDao == null) {
            logDao = new PostGreLogDao();
        }
        return logDao;
    }
    public FilmDao getFilmDao() {
        // retourne une implémentation de persistance.dao.FilmDao spécifique à SQL
        if (filmDao == null) {
            filmDao = new OMDbApiFilmDao();
        }
        return filmDao;
    }

    public WatchLaterDao getWatchLaterDao() {
        if (watchLaterDao == null) {
            watchLaterDao = new PostGreWatchLaterDao();
        }
        return watchLaterDao;
    }

  

    public RatingDao getRatingDao(){
        if (ratingDao == null) {
            ratingDao = new PostGreRatingDao();
        }
        return ratingDao;
    }

    // implémentations pour d'autres DAO si nécessaire

  
  

    @Override
    public UserMoodDao getUserMoodDao() {
        if (userMoodDao == null) {
            userMoodDao = new PostGreUserMoodDao(); // Crée une instance de PostGreUserMoodDao
        }
        return userMoodDao; // Retourne UserMoodDao
    }
  // implémentations pour d'autres DAO si nécessaire
    
}
