package persistance.dao;

import model.User;

public abstract class UserDao {
    public abstract void addUser(User user);
    public abstract User getUser(int userId);
    public abstract User getUser(String email);
    public abstract void updateUser(User user);
    public abstract void deleteUser(int userId);
}
