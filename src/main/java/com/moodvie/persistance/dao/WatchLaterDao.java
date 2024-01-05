package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.WatchLater;

import java.util.List;

public abstract class WatchLaterDao implements DaoInterface<WatchLater, Integer>{
    public abstract void add(WatchLater watchLater);
    public abstract WatchLater get(Integer watchLaterId);

    public abstract List<WatchLater> getAll(int userId);

    public abstract WatchLater get(String idFilm, int idUser);

    public abstract void update(WatchLater watchLater);
    public abstract void delete(Integer watchLaterId);

}
