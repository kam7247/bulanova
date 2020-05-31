package ru.egprojects.sw1_springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egprojects.sw1_springboot.model.Pages;

@Controller
@RequestMapping(Pages.SIGN_IN)
public class SignInController {

    @GetMapping
    public String get(Authentication authentication, Model model) {
        model.addAttribute("authenticated", authentication != null);
        return "sign_in";
    }

}
