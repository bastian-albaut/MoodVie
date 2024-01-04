package com.moodvie.persistance.factory;

import com.moodvie.persistance.dao.*;

/**
 * PostGreFactory est une implémentation de AbstractDaoFactory
 * Permet de gérer les DAO
 */

public class PostGreFactory extends AbstractDaoFactory {
    UserDao userDao;

    LogDao logDao;

    FilmDao filmDao;

    WatchLaterDao watchLaterDao;

    @Override
    public UserDao getUserDao() {
        // retourne une implémentation de persistance.dao.UserDao spécifique à SQL
        if (userDao == null) {
            userDao = new PostGreUserDao();
        }
        return userDao;
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
    // implémentations pour d'autres DAO si nécessaire
}
