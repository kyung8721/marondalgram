package com.kyung.marondalgram.like.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	
	public Like findByUserIdAndPostId(int userId, int postId);
	
	public int countByUserIdAndPostId(int userId, int postId);
	
	public int countByPostId(int postId);
	
}
