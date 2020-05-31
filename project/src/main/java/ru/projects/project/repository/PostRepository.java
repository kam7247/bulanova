package ru.egprojects.sw1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egprojects.sw1_springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
