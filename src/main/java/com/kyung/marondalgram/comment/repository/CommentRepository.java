package com.kyung.marondalgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyung.marondalgram.comment.domain.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	public List<Comment> findByPostId(int postId);
	
	public int countByPostId(int postId);
	
	public int countByUserIdAndPostIdAndComment(int userId, int postId, String comment);
	
	public Comment findByUserIdAndPostIdAndComment(int userId, int postId, String comment);
	
	public int countById(int commentId);
	
	@Transactional
	public void deleteByPostId(int postId);
}
