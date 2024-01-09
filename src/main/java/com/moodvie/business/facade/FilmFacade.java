package com.moodvie.business.facade;

import com.moodvie.persistance.dao.FilmDao;

import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Film;

import java.util.List;


public class FilmFacade {
    private final AbstractDaoFactory abstractDaoFactory;

    private static FilmFacade instance;

    public static synchronized FilmFacade getInstance() {
        if (instance == null) {
            instance = new FilmFacade();
        }
        return instance;
    }

    private FilmFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    /**
     * Get all films
     * @return List of films
     */

    public List<Film> searchFilms(String title) {
        FilmDao filmDao = abstractDaoFactory.getFilmDao();
        return filmDao.searchFilm(title);
    }

    /**
     * Get a film by its ID
     * @param id ID of the film
     * @return Film
     */

    public Film getFilm(String id) {
        FilmDao filmDao = abstractDaoFactory.getFilmDao();
        return filmDao.get(id);
    }

}
