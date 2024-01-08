package com.moodvie.persistance.factory;

import com.moodvie.persistance.dao.FilmDao;
import com.moodvie.persistance.dao.LogDao;
import com.moodvie.persistance.dao.UserDao;
import com.moodvie.persistance.dao.WatchLaterDao;
import com.moodvie.persistance.dao.UserMoodDao;

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
    abstract public LogDao getLogDao();
    abstract public FilmDao getFilmDao();

    abstract public WatchLaterDao getWatchLaterDao();

    //ajout lucas
    abstract public UserMoodDao getUserMoodDao();
}
