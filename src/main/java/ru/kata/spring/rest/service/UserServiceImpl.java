package ru.kata.spring.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.rest.dao.UserDao;
import ru.kata.spring.rest.model.User;

import java.util.List;


    @Service
    public class UserServiceImpl implements UserService {

        private UserDao userDao;

        private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        @Autowired
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        @Transactional
        public void saveUser(User user) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
        }

        @Override
        @Transactional(readOnly = true)
        public User getUser(Long id) {
            return userDao.get(id);
        }

        @Override
        @Transactional
        public void updateUser(User user) {
            if (!user.getPassword().equals(getUserByName(user.getUsername()).getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            userDao.update(user);
        }

        @Override
        @Transactional
        public void deleteUser(Long id) {
            userDao.delete(id);
        }

        @Override
        @Transactional(readOnly = true)
        public List<User> allUsers() {
            return userDao.getAllUsers();
        }

        @Override
        @Transactional(readOnly = true)
        public User getUserByName(String s) {
            return userDao.getUserByName(s);
        }



        }
