package ru.egprojects.sw1_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.egprojects.sw1_springboot.model.Post;
import ru.egprojects.sw1_springboot.model.FileInfo;
import ru.egprojects.sw1_springboot.model.User;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String photoSrc;
    private String country;
    private boolean isAuthor;
    private List<Post> posts;

    public static UserDto from(User user) {
        if (user == null) return null;
        FileInfo photo = user.getPhoto();
        String photoUrl = photo == null ? null : "/files/" + photo.getStorageFileName();

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                photoUrl,
                user.getCountry(),
                user.isAuthor(),
                user.getPosts()
        );
    }
}
