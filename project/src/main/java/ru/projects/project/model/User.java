package ru.egprojects.sw1_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private boolean isAuthor;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private FileInfo photo;
    private String country;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private Privacy privacy;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum Privacy {
        PRIVATE, OPENED
    }

    public enum State {
        NOT_CONFIRMED, CONFIRMED
    }
}