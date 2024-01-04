package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Film;

import java.util.List;

public abstract class FilmDao implements DaoInterface<Film , String> {
    public abstract void add(Film film);
    public abstract Film get(String filmId);

    public abstract List<Film> searchFilm(String title);
    public abstract void update(Film film);
    public abstract void delete(String filmId);

}
