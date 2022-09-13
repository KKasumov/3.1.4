package ru.kata.spring.rest.service;

import ru.kata.spring.rest.model.User;

import java.util.List;


    public interface UserService {

        void saveUser(User user);

        User getUser(Long id);

        void updateUser(User user);

        void deleteUser(Long id);

        List<User> allUsers();

        User getUserByName(String name);


    }
