package com.github.supercodinpj1.respository;

import com.github.supercodinpj1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByAuthor(String author);
}

