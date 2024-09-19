package com.kyung.marondalgram.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.post.domain.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{

}
