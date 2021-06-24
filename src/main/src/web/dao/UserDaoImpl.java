package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl(){}

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username like :username",User.class)
                .setParameter("username", username).getSingleResult();
    }
}