package com.github.supercodinpj1.respository.Comment;

import com.github.supercodinpj1.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment createComment(Long postId, String postedBy, String content);
    Optional<Comment> findByPostId(Long postId);
    Optional<Comment> findByCommentId(Long commentId);




}
