package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private UserDao userDao;

    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void createUser(String name, String surname, int age, String email, String username, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDao.createUser(user);
    }

    @Override
    public void editUser(long id, String name, String surname, int age, String email,String username, String password) {
        User user = getUserById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
