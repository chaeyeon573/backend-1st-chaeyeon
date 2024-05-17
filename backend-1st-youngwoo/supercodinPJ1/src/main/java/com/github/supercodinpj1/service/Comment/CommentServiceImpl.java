package com.github.supercodinpj1.service.Comment;

import com.github.supercodinpj1.domain.Comment;
import com.github.supercodinpj1.respository.Comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;



public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;




    @Override
    public Comment createComment(String commenter, String commentedBy, String content) {

            Comment comment = new Comment();
            comment.setCommenter(commenter);
            comment.setCommentedAt(new Date());
            comment.setContents(content);
            return commentRepository.save(comment);

    }

    @Override
    public Optional<Comment> getCommentsByPostId(Long id) {
        Optional<Comment> comments = commentRepository.findByPostId(id);

        return comments;
    }

        @Override
        public List<Comment> getCommentsByCommentId(Long commentId) {
            Optional<Comment> optionalComment = commentRepository.findByCommentId(commentId);

            return List.of(optionalComment.get());
        }


    @Override
    public Comment updateComment(Long commentId, String commenter, String content) {
        Optional<Comment> optionalComment = commentRepository.findByCommentId(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setCommenter(commenter);
            comment.setContents(content);
            comment.setCommentedAt(new Date());
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }



    @Override
    public boolean deleteComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(commentId);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void likeComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findByPostId(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
        }
    }

}
