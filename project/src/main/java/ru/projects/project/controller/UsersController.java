package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egprojects.sw1_springboot.service.UserService;
import ru.egprojects.sw1_springboot.model.Pages;

@Controller
@RequestMapping(Pages.USERS)
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

}