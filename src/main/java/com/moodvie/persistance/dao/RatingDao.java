package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Rating;

public abstract class RatingDao {

    public abstract void addRating(Rating rating);
    public abstract Rating getRating(int ratingId);
    public abstract Rating getRating(int idUser, String idFilm);
    public abstract void update(Rating rating);
    public abstract void delete(int ratingId);
    public abstract double getAverageRating(String idFilm); //chatgpt

}
