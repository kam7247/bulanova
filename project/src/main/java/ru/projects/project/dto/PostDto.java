package ru.egprojects.sw1_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.egprojects.sw1_springboot.model.FileInfo;
import ru.egprojects.sw1_springboot.model.Post;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String poster;
    private String description;
    private String genre;
    private int year;

    public static PostDto from(Post post) {
        FileInfo poster = post.getPoster();

        return new PostDto(
                post.getId(),
                post.getTitle(),
                poster == null ? null : "/files/" + poster.getStorageFileName(),
                post.getDescription(),
                post.getGenre(),
                post.getYear()
        );
    }
}
