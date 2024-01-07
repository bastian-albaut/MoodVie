package com.moodvie.business.facade;

import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Rating;

public class RatingFacade {

    private final AbstractDaoFactory abstractDaoFactory;
    private static RatingFacade instance;

     // Méthode statique pour obtenir une instance de RatingFacade de manière synchronisée
    public static synchronized RatingFacade getInstance() {
        if (instance == null) {
            instance = new RatingFacade();
        }
        return instance;
    }

    // Constructeur privé pour empêcher l'instanciation directe, initialise l'AbstractDaoFactory
    private RatingFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    // Méthode pour ajouter une note (Rating) à un film
    private void add(Rating rating) {
        abstractDaoFactory.getRatingDao().add(rating);
    }

    // Méthode pour récupérer une note (Rating) en fonction de l'ID utilisateur et de l'ID du film
    private Rating get(int idUser, String idFilm) {
        return abstractDaoFactory.getRatingDao().get(idUser, idFilm);
    }

    // Méthode pour mettre à jour une note (Rating)
    private void update(Rating rating) {
        abstractDaoFactory.getRatingDao().update(rating);
    }

    // Méthode pour supprimer une note (Rating) en fonction de l'ID de la note
    private void delete(int idRating) {
        abstractDaoFactory.getRatingDao().delete(idRating);
    }


    
}
