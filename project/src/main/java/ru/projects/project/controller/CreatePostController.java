package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.egprojects.sw1_springboot.dto.UserDto;
import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.security.details.UserDetailsImpl;
import ru.egprojects.sw1_springboot.service.FileStorageService;
import ru.egprojects.sw1_springboot.service.PostService;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(Pages.CREATE_POST)
public class CreatePostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String post(
            Authentication authentication,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam int year,
            @RequestParam String description,
            @RequestParam MultipartFile poster
    ) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Post post = postService.createPost(user, title, genre, year, description);

        post.setPoster(fileStorageService.saveFile(poster));
        postService.updatePost(post);

        List<Post> posts = user.getPosts();
        if (posts == null) posts = new ArrayList<>();
        posts.add(post);
        user.setPosts(posts);
        userRepository.save(user);

        return "redirect:" + Pages.POST + "?id=" + post.getId();
    }

    @GetMapping
    public String get(Authentication authentication, Model model) {
        model.addAttribute("authenticated", true);
        model.addAttribute("user", UserDto.from(
                ((UserDetailsImpl) authentication.getPrincipal()).getUser()
        ));

        return "create_post";
    }

}
