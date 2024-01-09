package com.moodvie.business.facade;

import com.moodvie.persistance.dao.LogDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Log;

public class LogFacade {
    private final AbstractDaoFactory abstractDaoFactory;

    private static LogFacade instance;

    /**
     * Liste des logs
     * @return la liste des logs
     */
    Log[] logs;


    public static synchronized LogFacade getInstance() {
        if (instance == null) {
            instance = new LogFacade();
        }
        return instance;
    }

    private LogFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    /**
     * Permet d'ajouter un log
     * @param log le log à ajouter
     */
    public void add(Log log) {
        LogDao logDao = abstractDaoFactory.getLogDao();
        logDao.add(log);
    }

    /**
     * Permet de récupérer tous les logs
     * @return la liste des logs
     */
    public Log[] getAll() {
        LogDao logDao = abstractDaoFactory.getLogDao();
        return logDao.getAll();
    }



}
