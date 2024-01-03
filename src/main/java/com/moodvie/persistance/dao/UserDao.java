package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.User;

public abstract class UserDao implements DaoInterface<User , Integer>{
    public abstract void add(User user);
    public abstract User get(Integer userId);
    public abstract User get(String email);
    public abstract void update(User user);
    public abstract void delete(Integer userId);
}
