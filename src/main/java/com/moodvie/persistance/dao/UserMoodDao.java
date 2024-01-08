package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.UserMood;

import java.util.List;

public abstract class UserMoodDao implements DaoInterface<UserMood, Integer> {
    public abstract void add(UserMood userMood);
    public abstract UserMood get(Integer moodID);

    public abstract List<UserMood> searchMood(String moodDescription);
    public abstract void update(UserMood userMood);
    public abstract void delete(Integer moodID);
    public abstract UserMood[] getAll();

}