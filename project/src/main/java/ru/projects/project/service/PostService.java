package ru.egprojects.sw1_springboot.service;

import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.model.User;

import java.util.List;

public interface PostService {
    Post createPost(User author, String title, String genre, int year, String desc);

    Post getPostById(Long id);

    void updatePost(Post post);

    List<Post> search(String query);

    List<Post> getLast(int count);
}
