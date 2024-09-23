package com.kyung.marondalgram.comment.service;

import org.springframework.stereotype.Service;

import com.kyung.marondalgram.comment.domain.Comment;
import com.kyung.marondalgram.comment.repository.CommentRepository;

@Service
public class CommentService {
	private CommentRepository commentRepository;
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public Comment additionCommentService(int userId, int postId, String comment) {
		Comment result = Comment.builder()
						.userId(userId)
						.postId(postId)
						.comment(comment)
						.build();
		
		 return commentRepository.save(result);
	}
}
