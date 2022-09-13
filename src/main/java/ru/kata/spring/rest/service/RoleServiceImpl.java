package ru.kata.spring.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.rest.dao.RoleDao;
import ru.kata.spring.rest.model.Role;

import java.util.List;



    @Service
    public class RoleServiceImpl implements RoleService {

        private RoleDao roleDao;

        @Autowired
        public void setRoleDao(RoleDao roleDao) {
            this.roleDao = roleDao;
        }

        @Override
        @Transactional
        public void saveRole(Role role) {
            roleDao.save(role);
        }

        @Override
        @Transactional(readOnly = true)
        public List<Role> allRoles() {
            return roleDao.getAllRoles();
        }

        @Override
        @Transactional(readOnly = true)
        public Role getRoleByName(String role) {
            return roleDao.getRoleByName(role);
        }
}
