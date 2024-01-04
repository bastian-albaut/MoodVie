package com.moodvie.business.facade;

import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.WatchLater;

import java.util.List;

public class WatchLaterFacade {
    private final AbstractDaoFactory abstractDaoFactory;

    private static WatchLaterFacade instance;

    public static synchronized WatchLaterFacade getInstance() {
        if (instance == null) {
            instance = new WatchLaterFacade();
        }
        return instance;
    }

    private WatchLaterFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    public void add(WatchLater watchLater) {
        abstractDaoFactory.getWatchLaterDao().add(watchLater);
    }

    public WatchLater get(String idFilm, int idUser) {
        return abstractDaoFactory.getWatchLaterDao().get(idFilm, idUser);
    }

    public List<WatchLater> getAll(int userId) {
        return abstractDaoFactory.getWatchLaterDao().getAll(userId);
    }

    public void delete(int idWatchLater) {
        abstractDaoFactory.getWatchLaterDao().delete(idWatchLater);
    }
}
