package ru.egprojects.sw1_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.model.User;
import ru.egprojects.sw1_springboot.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(User author, String title, String genre, int year, String desc) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .genre(genre)
                .year(year)
                .description(desc)
                .build();
        postRepository.save(post);

        return post;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.getOne(id);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> search(String query) {
        return postRepository.findAll()
                .stream()
                .filter(post -> post.getTitle().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getLast(int count) {
        List<Post> data = postRepository.findAll();
        if (data.size() > count) data = data.subList(data.size() - count - 1, data.size());

        return data;
    }
}
