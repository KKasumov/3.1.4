package ru.kata.spring.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.rest.model.Role;
import ru.kata.spring.rest.model.User;
import ru.kata.spring.rest.service.RoleService;
import ru.kata.spring.rest.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;



    @Configuration
    public class MvcConfig {

        private final UserService userService;
        private final RoleService roleService;

        @Autowired
        public MvcConfig(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
        }

        @Bean
        @PostConstruct
        public void addSomeUsers() {
            User user1 = new User();
            User user2 = new User();
            roleService.saveRole(new Role ("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));
            Set<Role> roles1 = new HashSet<> ();
            roles1.add(roleService.getRoleByName("ROLE_ADMIN"));
            roles1.add(roleService.getRoleByName("ROLE_USER"));
            Set<Role> roles2 = new HashSet<>();
            roles2.add(roleService.getRoleByName("ROLE_USER"));
            user1.setName("Арнольд");
            user1.setLastName ( "Шварценеггер" );
            user1.setPassword("1");

            user1.setRoles(roles1);
            userService.saveUser(user1);
            user2.setName("Брюс");
            user2.setLastName ( "Ли" );
            user2.setPassword("1");
            user2.setRoles(roles2);
            userService.saveUser(user2);
        }
}