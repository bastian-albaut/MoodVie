package persistance.dao;

public abstract class UserDao {
    public abstract void addUser(User user);
    abstract User getUser(int userId);
    abstract void updateUser(User user);
    abstract void deleteUser(int userId);
}
