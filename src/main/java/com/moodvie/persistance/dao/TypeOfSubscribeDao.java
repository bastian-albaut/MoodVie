package com.moodvie.persistance.dao;

import com.moodvie.persistance.model.TypeOfSubscribe;

public abstract class TypeOfSubscribeDao {
    public abstract void addTypeOfSubscribe(TypeOfSubscribe typeOfSubscribe);
    public abstract TypeOfSubscribe getTypeOfSubscribe(int subscribeId);
    public abstract void updateTypeOfSubscribe(TypeOfSubscribe subscribe);
    public abstract void deleteTypeOfSubscribe(int subscribeId);
}
