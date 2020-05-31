package ru.egprojects.sw1_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AuthData {
    @Id
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String passwordHash;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(unique = true)
    private String confirmationCode;

    public enum Role {
        USER, ADMIN
    }
}
