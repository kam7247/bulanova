package ru.egprojects.sw1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egprojects.sw1_springboot.model.AuthData;

import java.util.Optional;

public interface AuthDataRepository extends JpaRepository<AuthData, Long> {
    Optional<AuthData> findByEmail(String email);

    Optional<AuthData> findByConfirmationCode(String confirmationCode);
}

