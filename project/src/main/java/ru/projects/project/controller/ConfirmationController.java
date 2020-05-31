package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.security.details.UserDetailsImpl;
import ru.egprojects.sw1_springboot.service.PhoneService;
import ru.egprojects.sw1_springboot.service.UserService;

@Controller
public class ConfirmationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping(Pages.EMAIL_CONFIRMATION)
    public String confirmEmail(@RequestParam String code) {
        userService.confirmUser(code);
        return "redirect:/profile";
    }

    @GetMapping(Pages.PHONE_CONFIRMATION)
    public String getPhoneConfirmationPage(Model model) {
        model.addAttribute("authenticated", true);
        model.addAttribute("action", "/confirm/phone/send");

        return "phone_confirmation";
    }

    @PostMapping(Pages.PHONE_CONFIRMATION + "/send")
    public String sendCode(Authentication authentication, Model model, @RequestParam String phoneNumber) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        phoneService.prepareConfirmation(user, phoneNumber);

        model.addAttribute("authenticated", true);
        model.addAttribute("action", Pages.PHONE_CONFIRMATION);

        return "phone_confirmation";
    }

    @PostMapping(Pages.PHONE_CONFIRMATION)
    public void confirmPhone(@RequestParam String code) {
        phoneService.confirm(code);
    }

}
