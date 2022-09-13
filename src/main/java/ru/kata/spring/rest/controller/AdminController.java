/*
package ru.kata.spring.rest.controller;

import ru.kata.spring.rest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "pages/login";
    }

    @GetMapping(value = "/index")
    public String getIndexPage(ModelMap model, Principal principal) {
        model.addAttribute("ourUser", userService.loadUserByUsername(principal.getName()));
        return "pages/index";
    }

}
*/
