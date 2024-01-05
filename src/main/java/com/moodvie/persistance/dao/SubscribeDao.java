package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.Subscribe;

public abstract class SubscribeDao {
    public abstract void addSubscribe(Subscribe subscribe);
    public abstract Subscribe getSubscribe(int subscribeId);
    public abstract Subscribe getSubscribe(String email);
    public abstract void updateSubscribe(Subscribe subscribe);
    public abstract void deleteSubscribe(int subscribeId);
}
