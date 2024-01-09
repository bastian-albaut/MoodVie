package com.moodvie.business.facade;

import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.Rating;
import java.util.List;

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
    public RatingFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    // Méthode pour ajouter une note (Rating) à un film
    public void add(Rating rating) {
        abstractDaoFactory.getRatingDao().addRating(rating);
    }

    // Méthode pour récupérer une note (Rating) en fonction de l'ID utilisateur et de l'ID du film
    public Rating get(int idUser, String idFilm) {
        return abstractDaoFactory.getRatingDao().getRating(idUser, idFilm);
    }

    public Rating get(int idRating) {
        return abstractDaoFactory.getRatingDao().getRating(idRating);
    }

    /* 
    public List<Rating> getAll(int userId) {
        return abstractDaoFactory.getRatingDao().getAll(userId);
    }
    */

    // Méthode pour mettre à jour une note (Rating)
    public void update(Rating rating) {
        abstractDaoFactory.getRatingDao().update(rating);
    }

    public void updateComment(Rating rating) {
        abstractDaoFactory.getRatingDao().updateComment(rating);
    }


    // Méthode pour supprimer une note (Rating) en fonction de l'ID de la note
    public void delete(int idRating) {
        abstractDaoFactory.getRatingDao().delete(idRating);
    }


    // Méthode pour récupérer la note moyenne d'un film
    public double getAverageRating(String idFilm) {
        return abstractDaoFactory.getRatingDao().getAverageRating(idFilm);
    }

    // Méthode pour récupérer les commentaires d'un film
    public String getComments(String idFilm) {
        return abstractDaoFactory.getRatingDao().getComments(idFilm);
    }


    
}
