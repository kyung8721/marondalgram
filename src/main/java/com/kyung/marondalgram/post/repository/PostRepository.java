package com.kyung.marondalgram.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.post.domain.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{
	public List<Post> findAllByOrderByUpdatedAtDesc();
	
	public Optional<Post> findByIdAndUserId(int postId, int userId);
}
