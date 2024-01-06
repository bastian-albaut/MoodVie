package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Rating;

public abstract class RatingDao {

    public abstract void add(Rating rating);
    public abstract Rating get(int ratingId);
    public abstract Rating get(int idUser, String idFilm);
    public abstract void update(Rating rating);
    public abstract void delete(int ratingId);


}
