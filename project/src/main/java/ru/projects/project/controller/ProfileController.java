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
import ru.egprojects.sw1_springboot.model.FileInfo;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.security.details.UserDetailsImpl;
import ru.egprojects.sw1_springboot.service.FileStorageService;
import ru.egprojects.sw1_springboot.service.UserService;
import ru.egprojects.sw1_springboot.repository.FileInfoRepository;
import ru.egprojects.sw1_springboot.repository.UserRepository;

@Controller
@RequestMapping(Pages.PROFILE)
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String get(
            Authentication authentication,
            @RequestParam(required = false) Long id,
            Model model
    ) {
        if (authentication == null && id == null) return "redirect:" + Pages.SIGN_IN;

        UserDto profile = id != null ? userService.getById(id) : UserDto.from(
                ((UserDetailsImpl) authentication.getPrincipal()).getUser()
        );
        model.addAttribute("isItMe", id == null);
        model.addAttribute("profile", profile);
        model.addAttribute("authenticated", authentication != null);

        return "profile";
    }

    @PostMapping
    public String uploadPhoto(Authentication authentication, @RequestParam("photo") MultipartFile file) {
        FileInfo fileInfo = fileStorageService.saveFile(file);
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        user.setPhoto(fileInfo);
        userRepository.save(user);

        return "redirect:/profile";
    }
}
