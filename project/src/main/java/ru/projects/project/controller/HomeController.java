package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egprojects.sw1_springboot.dto.UserDto;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.service.PostService;
import ru.egprojects.sw1_springboot.service.UserService;
import ru.egprojects.sw1_springboot.dto.PostDto;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(Pages.HOME)
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    public String get(Authentication authentication, Model model) {
        model.addAttribute("authenticated", authentication != null);
        List<UserDto> lastAuthors = userService.getLastAuthors(10)
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
        model.addAttribute("authors", lastAuthors);
        List<PostDto> lastPosts = postService.getLast(10)
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
        model.addAttribute("posts", lastPosts);

        return "home";
    }

}
