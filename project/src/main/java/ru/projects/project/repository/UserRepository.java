package ru.egprojects.sw1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egprojects.sw1_springboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByIsAuthor(Boolean artist);
}
