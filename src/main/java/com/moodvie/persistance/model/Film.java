package com.moodvie.persistance.model;


public class Film {
    private String Title;

    private String Year;

    private String imdbID;

    private String Type;

    private String Poster;

    private String Plot;

    private String Genre;

    private String Director;

    private String Actors;


    public Film() {
    }


    public String getId() {
        return imdbID;

    public void setTitle(String Title) {
        this.Title = Title;

    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    public String getPlot() {
        return Plot;
    }

    public String getGenre() {
        return Genre;
    }
   
    public String toString() {
        // Cette méthode est utilisée par la ComboBox pour afficher le texte du film
        return Title; // ou tout autre format que vous préférez
    }
}
