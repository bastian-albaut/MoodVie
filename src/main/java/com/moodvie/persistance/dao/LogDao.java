package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Log;

import java.util.ArrayList;

public abstract class LogDao implements DaoInterface<Log , Integer>{
    public abstract void add(Log log);
    public abstract Log get(Integer logId);
    public abstract void update(Log log);
    public abstract void delete(Integer logId);
    /**
     * Permet de récupérer tous les logs
     * @return la liste des logs
     */
    public abstract Log[] getAll();
}
