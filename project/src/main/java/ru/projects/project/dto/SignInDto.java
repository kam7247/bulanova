package ru.egprojects.sw1_springboot.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}
