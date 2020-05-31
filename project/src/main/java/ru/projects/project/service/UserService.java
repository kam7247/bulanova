package ru.egprojects.sw1_springboot.service;

import ru.egprojects.sw1_springboot.dto.UserDto;
import ru.egprojects.sw1_springboot.model.User;

import java.util.List;

public interface UserService {
    UserDto getById(Long id);

    List<UserDto> getAll();

    boolean confirmUser(String confirmationCode);

    List<User> getLastAuthors(int count);
}
