package ru.kata.spring.rest.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.rest.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


    @Repository
    public class RoleDaoImpl implements RoleDao {

        @PersistenceContext
        EntityManager entityManager;

        @Override
        public void save(Role role) {
            entityManager.persist(role);
        }


        @Override
        public List<Role> getAllRoles() {
            return entityManager.createQuery("SELECT role FROM Role role", Role.class).getResultList();
        }

        @Override
        public Role getRoleByName(String role) {
            return entityManager.createQuery("select role from Role role where role.role=:role",
                    Role.class).setParameter("role", role).getSingleResult();

        }
}
