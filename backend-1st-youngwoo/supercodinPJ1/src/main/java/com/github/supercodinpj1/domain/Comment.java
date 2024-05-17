package com.github.supercodinpj1.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;//commentid

    @Column(nullable = false, length = 500)
    private String contents;//댓글 내용

//    private String postedBy;

//    @Column(nullable = false, length = 255)
//    private String commentedBy;//댓글을 한 사람(author->commentedBy)
//
//    private Long commentedId;//댓글을 한 사람의 id(추가)
    @Column(nullable = false)
    private String commenter;

    @Column(nullable = false)
    private Date commentedAt;//댓글을 작성한 시점

    private int likeCount;//댓글의 좋아요

    @ManyToOne
    @JoinColumn(name = "author",nullable = false)
    private Post post;

}


//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//  }
