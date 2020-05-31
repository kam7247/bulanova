package ru.egprojects.sw1_springboot.service;

import ru.egprojects.sw1_springboot.model.User;

public interface PhoneService {
    void prepareConfirmation(User user, String phoneNumber);

    boolean confirm(String code);
}
