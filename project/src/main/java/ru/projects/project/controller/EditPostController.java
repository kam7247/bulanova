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
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.PostRepository;
import ru.egprojects.sw1_springboot.security.details.UserDetailsImpl;
import ru.egprojects.sw1_springboot.service.FileStorageService;
import ru.egprojects.sw1_springboot.dto.PostDto;

@Controller
@RequestMapping(Pages.EDIT_POST)
public class EditPostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String get(Authentication authentication, @RequestParam Long id, Model model) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        Post post = postRepository.getOne(id);
        if (!post.getAuthor().getId().equals(user.getId())) {
            return "redirect:" + Pages.POST + "?id=" + id;
        }
        model.addAttribute("authenticated", true);
        model.addAttribute("user", UserDto.from(user));
        model.addAttribute("post", PostDto.from(postRepository.getOne(id)));

        return "edit_post";
    }

    @PostMapping
    public String post(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam MultipartFile poster,
            @RequestParam String genre,
            @RequestParam int year
    ) {
        Post post = postRepository.getOne(id);
        post.setTitle(title);
        post.setPoster(fileStorageService.saveFile(poster));
        post.setGenre(genre);
        post.setYear(year);
        ;

        postRepository.save(post);

        return "redirect:" + Pages.POST + "?id=" + id;
    }

}
