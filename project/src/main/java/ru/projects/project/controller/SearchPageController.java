package ru.egprojects.sw1_springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egprojects.sw1_springboot.model.Pages;

@Controller
@RequestMapping(Pages.SEARCH)
public class SearchPageController {

    @GetMapping
    public String get(Authentication authentication, Model model, @RequestParam String query) {
        model.addAttribute("authenticated", authentication != null);
        model.addAttribute("query", query);
        return "search";
    }
}
