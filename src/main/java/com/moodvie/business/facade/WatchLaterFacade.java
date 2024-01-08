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

    /**
     * Add a watchLater
     * @param watchLater watchLater to add
     */
    public void add(WatchLater watchLater) {
        abstractDaoFactory.getWatchLaterDao().add(watchLater);
    }

    /**
     * Get a watchLater
     * @param idFilm ID of the film
     * @param idUser ID of the user
     * @return WatchLater
     */
    public WatchLater get(String idFilm, int idUser) {
        return abstractDaoFactory.getWatchLaterDao().get(idFilm, idUser);
    }
    /**
     * Get all watchLater
     * @param userId ID of the user
     * @return List of watchLater
     */
    public List<WatchLater> getAll(int userId) {
        return abstractDaoFactory.getWatchLaterDao().getAll(userId);
    }

    /**
     * Delete a watchLater
     * @param idWatchLater ID of the watchLater
     */
    public void delete(int idWatchLater) {
        abstractDaoFactory.getWatchLaterDao().delete(idWatchLater);
    }
}
