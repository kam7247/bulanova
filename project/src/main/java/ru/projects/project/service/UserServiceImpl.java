package ru.egprojects.sw1_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.egprojects.sw1_springboot.dto.UserDto;
import ru.egprojects.sw1_springboot.model.AuthData;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.AuthDataRepository;
import ru.egprojects.sw1_springboot.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthDataRepository authDataRepository;

    @Override
    public UserDto getById(Long id) {
        return UserDto.from(userRepository.getOne(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public boolean confirmUser(String confirmationCode) {
        Optional<AuthData> authDataOptional = authDataRepository.findByConfirmationCode(confirmationCode);
        if (authDataOptional.isPresent()) {
            User user = authDataOptional.get().getUser();
            user.setState(User.State.CONFIRMED);
            userRepository.save(user);

            return true;
        } else return false;
    }

    @Override
    public List<User> getLastAuthors(int count) {
        List<User> authors = userRepository.findByIsAuthor(true);
        if (authors.size() > count) authors = authors.subList(authors.size() - 1 - count, authors.size());

        return authors;
    }
}
