package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    void createUser(String name, String surname, int age, String email, String username, String password);
    void editUser(long id, String name, String surname, int age, String email, String username, String password);
    void deleteUser(long id);
}
