package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egprojects.sw1_springboot.dto.SignUpDto;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.service.SignUpService;

@Controller
@RequestMapping(Pages.SIGN_UP)
public class SignUpController {
    @Autowired
    private SignUpService service;

    @GetMapping
    public String get(Authentication authentication, Model model) {
        model.addAttribute("authenticated", authentication != null);
        return "sign_up";
    }

    @PostMapping
    public String post(SignUpDto form) {
        service.signUp(form);
        return "redirect:/signIn";
    }
}
