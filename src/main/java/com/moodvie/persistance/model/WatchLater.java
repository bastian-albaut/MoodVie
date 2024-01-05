package com.moodvie.persistance.model;

public class WatchLater {
    private int idWatchLater;
    private int idUser;

    private String idFilm;

    public WatchLater() {
    }

    public WatchLater(int idUser, String idFilm) {
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

    public int getIdWatchLater() {
        return idWatchLater;
    }

    public void setId(int idWatchLater) {
        this.idWatchLater = idWatchLater;
    }


}
