package ru.egprojects.sw1_springboot.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.egprojects.sw1_springboot.dto.SignUpDto;
import ru.egprojects.sw1_springboot.model.AuthData;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.AuthDataRepository;
import ru.egprojects.sw1_springboot.repository.UserRepository;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AuthDataRepository authDataRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExecutorService threadPool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void signUp(SignUpDto form) {
        User user = User.builder()
                .username(form.getUsername())
                .email(form.getEmail())
                .isAuthor(Boolean.parseBoolean(form.getIsAuthor()))
                .privacy(User.Privacy.OPENED)
                .state(User.State.NOT_CONFIRMED)
                .build();
        usersRepository.save(user);

        String passwordHash = passwordEncoder.encode(form.getPassword());
        AuthData authData = AuthData.builder()
                .email(form.getEmail())
                .user(user)
                .passwordHash(passwordHash)
                .confirmationCode(UUID.randomUUID().toString())
                .role(AuthData.Role.USER)
                .build();
        authDataRepository.save(authData);

        threadPool.submit(() -> {
            try {
                Template template = freeMarkerConfig.getTemplate("letter.ftlh");
                emailService.sendMail(
                        user.getEmail(),
                        "Registration",
                        FreeMarkerTemplateUtils.processTemplateIntoString(template, authData)
                );
            } catch (TemplateException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
