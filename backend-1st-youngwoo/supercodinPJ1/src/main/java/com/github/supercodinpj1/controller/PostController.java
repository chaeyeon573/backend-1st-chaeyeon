package com.github.supercodinpj1.controller;


import com.github.supercodinpj1.domain.Post;
import com.github.supercodinpj1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor // @Non null + static 생성자
@RestController
public class PostController {

    private final PostService postService;


    // 전체 게시물 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getBoards() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }


    // ex) localhost:8080/api/posts/1
    // id로 게시글 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }



    // localhost:8080/api/posts
    // POST 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<?> save(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity<>("게시글이 정상적으로 작성 완료되었습니다.", HttpStatus.CREATED);
    }


    // PUT 게시글 수정
    // ex) localhost:8080/api/posts/3
    @PutMapping("/posts/{id}")
    public ResponseEntity<?> editPost(@PathVariable("id") Long id, @RequestBody Post updatePost) {
        postService.editPost(id, updatePost);
        return new ResponseEntity<>("게시글이 정상적으로 수정 완료되었습니다.", HttpStatus.OK);
    }

    // DELETE 게시글 삭제
    // ex) localhost:8080/api/posts/3
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("게시글이 정상적으로 삭제 완료되었습니다.", HttpStatus.OK);
    }

    // 작성자로 게시글 조회
    // ex) localhost:8080/api/posts/search?author_email=사용자이메일@example.com
    @GetMapping("/posts/search")
    public ResponseEntity<List<Post>> getPostsByAuthor(@RequestParam("author_email") String authorEmail)    {
        List<Post> posts = postService.getPostsByAuthor(authorEmail);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}