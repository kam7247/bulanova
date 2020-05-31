package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egprojects.sw1_springboot.dto.UserDto;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.dto.PostDto;
import ru.egprojects.sw1_springboot.repository.PostRepository;

@Controller
@RequestMapping(Pages.POST)
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String get(Authentication authentication, @RequestParam Long id, Model model) {
        model.addAttribute("authenticated", authentication != null);
        Post post = postRepository.getOne(id);
        model.addAttribute("post", PostDto.from(post));
        model.addAttribute("author", UserDto.from(post.getAuthor()));

        return "post";
    }
}
