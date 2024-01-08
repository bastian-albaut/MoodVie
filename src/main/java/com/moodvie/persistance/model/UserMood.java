package com.moodvie.persistance.model;

import java.util.ArrayList;
import java.util.List;

public class UserMood {
    private int moodID; // Identifiant unique pour l'humeur
    private String moodDescription; // Description de l'humeur
    private List<String> associatedFilmIDs; // Liste des identifiants des films associés à l'humeur
    private int userId;

    public UserMood() {
        this.associatedFilmIDs = new ArrayList<>(); // Initialiser la liste des films
    }

    // Getters et Setters

    public int getMoodID() {
        return moodID;
    }

    public void setMoodID(int moodID) {
        this.moodID = moodID;
    }

    public String getMoodDescription() {
        return moodDescription;
    }

    public void setMoodDescription(String moodDescription) {
        this.moodDescription = moodDescription;
    }

    public List<String> getAssociatedFilmIDs() {
        return associatedFilmIDs;
    }

    public void setAssociatedFilmIDs(List<String> associatedFilmIDs) {
        this.associatedFilmIDs = associatedFilmIDs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    // Méthode pour ajouter un seul film associé
    public void addAssociatedFilmID(String filmID) {
        if (!this.associatedFilmIDs.contains(filmID)) {
            this.associatedFilmIDs.add(filmID);
        }
    }

    // Méthode pour supprimer un film associé
    public void removeAssociatedFilmID(String filmID) {
        this.associatedFilmIDs.remove(filmID);
    }
    
    // ... vous pouvez ajouter d'autres méthodes selon vos besoins ...
}
