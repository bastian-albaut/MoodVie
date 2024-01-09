package com.moodvie.persistance.model;

public class Rating {

    private int idRating;
    private int idUser;
    private String idFilm;
    private int value;
    private String comment;

    public Rating() {
    }

    public Rating(int idUser, String idFilm, int value, String comment) {
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.value = value;
        this.comment = comment;
    }

    // Getters et setters

    public int getIdRating() {
        return idRating;
    }   

    public void setIdRating(int idRating) {
        this.idRating = idRating;
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

    public int getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "idRating=" + idRating +
                ", idUser=" + idUser +
                ", idFilm='" + idFilm + '\'' +
                ", value=" + value +
                ", comment='" + comment + '\'' +
                '}';
    }


    
    
}
