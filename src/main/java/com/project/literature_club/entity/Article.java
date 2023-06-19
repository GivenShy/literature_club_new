package com.project.literature_club.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;


    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Long likeCount;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "likedArticles")
    Set<User> likes;

}
