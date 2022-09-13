package ru.kata.spring.rest.dao;

import ru.kata.spring.rest.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository


    public interface UserDao {

        User getUserByName(String name);

        void save(User user);

        User get(Long id);

        void update(User user);

        void delete(Long id);

        List<User> getAllUsers();
}
