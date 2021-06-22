package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(long id);
    void createUser(User user);
    void editUser(User user);
    void deleteUser(long id);
    User loadUserByUsername(String username);
}
