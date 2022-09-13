package ru.kata.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.rest.model.Role;
import ru.kata.spring.rest.model.User;
import ru.kata.spring.rest.service.RoleService;
import ru.kata.spring.rest.service.UserService;

import java.util.List;


    @RestController
    @RequestMapping(value = "/")
    public class AdminRestController {

        private final UserService userService;
        private final RoleService roleService;

        @Autowired
        public AdminRestController(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
        }


        @GetMapping("admin/allUsers")
        public List<User> showAllUsers() {
            return userService.allUsers();
        }


        @GetMapping("admin/{id}")
        public User getUser(@PathVariable long id) {
            return userService.getUser(id);
        }

        @GetMapping("admin/authorities")
        public List<Role> showAllRoles() {
            return roleService.allRoles();
        }


        @PostMapping("admin")
        public User addNewUser(@RequestBody User user) {
            userService.saveUser(user);
            return user;
        }


        @PutMapping("admin")
        public User updateUser(@RequestBody User user) {
            userService.updateUser(user);
            return user;
        }

        @DeleteMapping("admin/{id}")
        public String deleteUser(@PathVariable long id) {
            userService.deleteUser(id);
            return "User with ID = " + id + " was deleted";
        }

    }
