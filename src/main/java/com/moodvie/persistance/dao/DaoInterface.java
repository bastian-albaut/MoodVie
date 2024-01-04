package com.moodvie.persistance.dao;


public interface DaoInterface<Object, ID> {
    public void add(Object object);
    public Object get(ID id);
    public void update(Object object);
    public void delete(ID id);
}
