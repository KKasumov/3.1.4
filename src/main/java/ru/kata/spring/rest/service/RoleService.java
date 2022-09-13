package ru.kata.spring.rest.service;

import ru.kata.spring.rest.model.Role;

import java.util.List;


    public interface RoleService {

        void saveRole(Role role);

        List<Role> allRoles();

        Role getRoleByName(String role);
}
