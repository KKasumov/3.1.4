/*
package ru.kata.spring.rest.controller;

import ru.kata.spring.rest.model.User;
import ru.kata.spring.rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getAuthUser() {
        User user = userService.authUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
*/
