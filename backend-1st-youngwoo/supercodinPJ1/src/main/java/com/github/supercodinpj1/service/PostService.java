package com.github.supercodinpj1.service;


import com.github.supercodinpj1.domain.Post;
import com.github.supercodinpj1.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> getAllPost() {
        List<Post> boards = postRepository.findAll();
        return boards;
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Long id, Post updatePost) {
        Post post = postRepository.findById(id).get();
        post.setTitle(updatePost.getTitle());
        post.setContent(updatePost.getContent());

        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<Post> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }
}