package ru.egprojects.sw1_springboot.service;

import ru.egprojects.sw1_springboot.dto.SignUpDto;

public interface SignUpService {
    void signUp(SignUpDto form);
}
