package ru.kata.spring.rest.dao;

import ru.kata.spring.rest.model.Role;

import java.util.List;



    public interface RoleDao {

        void save(Role role);

        List<Role> getAllRoles();

        Role getRoleByName(String role);
}
