package com.github.supercodinpj1.service.Comment;



import com.github.supercodinpj1.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {

    Comment createComment(String commenter, String commentedBy, String content);


    Optional<Comment> getCommentsByPostId(Long id);
    List<Comment> getCommentsByCommentId(Long commentId);

    void likeComment(Long postId);

    Comment updateComment(Long commentId, String commenter, String content);
    boolean deleteComment(Long commentId);
}
