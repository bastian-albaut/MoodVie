package com.moodvie.persistance.model;

public class Rating {

    private int idRating;
    private int idUser;
    private String idFilm;

    public Rating() {
    }

    public Rating(int idUser, String idFilm) {
        this.idUser = idUser;
        this.idFilm = idFilm;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setId(int idRating) {
        this.idRating = idRating;
    }

    
    
}
