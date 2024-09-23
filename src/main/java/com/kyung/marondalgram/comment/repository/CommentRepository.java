package com.kyung.marondalgram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
