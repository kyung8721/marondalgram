package com.kyung.marondalgram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.post.domain.Post;
import com.kyung.marondalgram.post.dto.PostDto;


public interface PostRepository extends JpaRepository<Post, Integer>{
	public List<Post> findAllByOrderByUpdatedAtDesc();
}
