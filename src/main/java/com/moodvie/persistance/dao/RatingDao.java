package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Rating;
import java.util.List;

public abstract class RatingDao {

    public abstract void addRating(Rating rating);
    public abstract Rating getRating(int ratingId);
    public abstract Rating getRating(int idUser, String idFilm);
    //public abstract List<Rating> getAll(int userId);
    public abstract void update(Rating rating);
    public abstract void updateComment(Rating rating);
    public abstract void delete(int ratingId);


    public abstract double getAverageRating(String idFilm); 
    public abstract String getComments(String idFilm);
    
  

}
