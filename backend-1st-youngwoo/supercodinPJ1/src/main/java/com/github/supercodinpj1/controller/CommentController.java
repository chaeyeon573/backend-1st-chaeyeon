package com.github.supercodinpj1.controller;

import com.github.supercodinpj1.domain.Comment;
import com.github.supercodinpj1.service.Comment.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestParam String commenter, @RequestParam String commentedBy, @RequestParam String content) {
        try {
            Comment comment = commentService.createComment(commenter, commentedBy, content);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Optional<Comment>> getCommentsByPostId(Long id) {
        try {
            Optional<Comment> comments = commentService.getCommentsByPostId(id);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<List<Comment>> getCommentsByCommentId(Long commentId) {
        try {
            List<Comment> comments = commentService.getCommentsByCommentId(commentId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestParam String commenter,  @RequestParam String content) {
        try {
            Comment updatedComment = commentService.updateComment(commentId, commenter, content);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        try {
            boolean deleted = commentService.deleteComment(commentId);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

