package com.github.supercodinpj1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data // Getter + Setter
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 인스턴스를 받는 생성자
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 인스턴스를 생성하면, 자동으로 인덱스를 붙힘 -> 1, 2, ,
    private Long id;


    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String content;


    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private int likeCount;//댓글의 좋아요

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}



