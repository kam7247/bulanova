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
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.UserRepository;
import ru.egprojects.sw1_springboot.security.details.UserDetailsImpl;
import ru.egprojects.sw1_springboot.service.FileStorageService;

@Controller
@RequestMapping(Pages.EDIT_PROFILE)
public class EditProfileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String get(Authentication authentication, Model model) {
        if (authentication == null) return "redirect:/profile";
        UserDto profile = UserDto.from(
                ((UserDetailsImpl) authentication.getPrincipal()).getUser()
        );
        model.addAttribute("isItMe", true);
        model.addAttribute("profile", profile);
        model.addAttribute("authenticated", true);

        return "edit_profile";
    }

    @PostMapping
    public String post(
            Authentication authentication,
            @RequestParam("photo") MultipartFile file,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String country
    ) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        user.setPhoto(fileStorageService.saveFile(file));
        user.setUsername(username);
        user.setEmail(email);
        user.setCountry(country);
        userRepository.save(user);

        return "redirect:/profile";
    }

}
