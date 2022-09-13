package ru.kata.spring.rest.dao;

import ru.kata.spring.rest.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

    public class UserDaoImpl implements UserDao {

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public User getUserByName(String name) {
            return entityManager.createQuery("SELECT user FROM User user WHERE user.name=:username",
                    User.class).setParameter("username", name).getSingleResult();
        }

        @Override
        public void save(User user) {
            entityManager.persist(user);
        }

        @Override
        public User get(Long id) {
            return entityManager.find(User.class, id);
        }

        @Override
        public void update(User user) {
            entityManager.merge(user);
        }

        @Override
        public void delete(Long id) {
            entityManager.createQuery("DELETE FROM User user WHERE user.id = :id")
                    .setParameter("id", id).executeUpdate();
        }

        @Override
        public List getAllUsers() {
            return entityManager.createQuery("from User").getResultList();
        }
    }


