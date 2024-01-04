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

    public List<Film> searchFilms(String title) {
        FilmDao filmDao = abstractDaoFactory.getFilmDao();
        return filmDao.searchFilm(title);
    }

    public Film getFilm(String id) {
        FilmDao filmDao = abstractDaoFactory.getFilmDao();
        return filmDao.get(id);
    }

}
