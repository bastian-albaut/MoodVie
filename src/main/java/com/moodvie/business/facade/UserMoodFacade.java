package com.moodvie.business.facade;


import com.moodvie.persistance.dao.UserMoodDao;
import com.moodvie.persistance.factory.AbstractDaoFactory;
import com.moodvie.persistance.model.UserMood;

import com.moodvie.persistance.dao.OMDbApiFilmDao;
import com.moodvie.persistance.model.Film;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class UserMoodFacade {
    private final AbstractDaoFactory abstractDaoFactory;

    private static UserMoodFacade instance; //singleton

    public static synchronized UserMoodFacade getInstance() {
        if (instance == null) {
            instance = new UserMoodFacade();
        }
        return instance;
    }

    private UserMoodFacade() {
        this.abstractDaoFactory = AbstractDaoFactory.getFactory();
    }

    // Exemple de méthode pour ajouter un UserMood
    public void addUserMood(UserMood userMood) {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
        userMoodDao.add(userMood);
    }

    // Exemple de méthode pour obtenir un UserMood par son ID
    public UserMood getUserMood(int moodID) {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
        return userMoodDao.get(moodID);
    }

    // Exemple de méthode pour rechercher des UserMoods par description
    public List<UserMood> searchUserMoods(String moodDescription) {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
        return userMoodDao.searchMood(moodDescription);
    }
    //MAJ Mood
    public boolean updateUserMood(UserMood userMood) {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
        try {
            userMoodDao.update(userMood);
            return true;
        } catch (Exception e) {
            // Gestion des exceptions
            return false;
        }
    }
   // supprimer un mood
public boolean deleteUserMood(Integer moodID) {
    UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
    try {
        userMoodDao.delete(moodID);
        return true;
    } catch (Exception e) {
        // Gestion des exceptions en cas d'échec de la suppression
        return false;
    }
}

    
    
    //filter tout les moods

    public List<UserMood> getAllUserMoods() {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
        UserMood[] userMoodsArray = userMoodDao.getAll();
        return Arrays.asList(userMoodsArray); // Convertissez le tableau en liste
    }
    
    public void addOrUpdateUserMood(UserMood userMood) {
        UserMoodDao userMoodDao = abstractDaoFactory.getUserMoodDao();
    
        // Vérifier si le mood existe déjà par la description
        List<UserMood> existingMoods = userMoodDao.searchMood(userMood.getMoodDescription());
    
        if (existingMoods.isEmpty()) {
            // Si le mood n'existe pas, ajoutez-le à la base de données
            userMoodDao.add(userMood);
        } else {
            // Si le mood existe déjà, récupérez l'ID et mettez à jour l'entrée existante
            UserMood existingMood = existingMoods.get(0); // Prendre le premier élément comme exemple
            userMood.setMoodID(existingMood.getMoodID()); // Assurez-vous que l'ID est défini pour la mise à jour
    
            // Mettre à jour la liste des films associés si nécessaire
            // Par exemple, en ajoutant de nouveaux films à la liste existante
            List<String> updatedFilmIDs = new ArrayList<>(existingMood.getAssociatedFilmIDs());
            updatedFilmIDs.addAll(userMood.getAssociatedFilmIDs()); // Ajouter de nouveaux films
            userMood.setAssociatedFilmIDs(updatedFilmIDs); // Mettre à jour les films associés
    
            userMoodDao.update(userMood); // Mettre à jour l'entrée existante avec les nouvelles informations
        }
    }
    
    public List<Film> getFilmsByMood(UserMood userMood) {
        // Créez une instance de votre OMDbApiFilmDao pour récupérer les films
        OMDbApiFilmDao filmDao = new OMDbApiFilmDao();
        List<Film> films = new ArrayList<>();
    
        // Supposons que UserMood a une méthode getAssociatedFilmIDs qui retourne une liste d'ID de films
        for (String filmId : userMood.getAssociatedFilmIDs()) {
            Film film = filmDao.get(filmId);
            if (film != null) {
                films.add(film);
            }
        }
    
        return films;
    }


    // Autres méthodes nécessaires pour gérer UserMood
    // ...

}
