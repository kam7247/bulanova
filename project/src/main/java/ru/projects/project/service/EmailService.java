package ru.egprojects.sw1_springboot.service;

public interface EmailService {
    void sendMail(String email, String subject, String text);
}
