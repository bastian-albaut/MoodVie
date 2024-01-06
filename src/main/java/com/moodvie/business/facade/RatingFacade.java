package com.moodvie.business.facade;

import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Rating;

public class RatingFacade {

    private final AbstractDaoFactory abstractDaoFactory;
    private static RatingFacade instance;

    public static synchronized RatingFacade getInstance() {
        if (instance == null) {
            instance = new RatingFacade();
        }
        return instance;
    }

    private RatingFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    private void add(Rating rating) {
        abstractDaoFactory.getRatingDao().add(rating);
    }

    private Rating get(int idUser, String idFilm) {
        return abstractDaoFactory.getRatingDao().get(idUser, idFilm);
    }

    private void delete(int idRating) {
        abstractDaoFactory.getRatingDao().delete(idRating);
    }


    
}
