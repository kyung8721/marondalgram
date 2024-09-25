package com.kyung.marondalgram.comment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyung.marondalgram.comment.domain.Comment;
import com.kyung.marondalgram.comment.dto.CommentDto;
import com.kyung.marondalgram.comment.repository.CommentRepository;
import com.kyung.marondalgram.user.dto.UserDto;
import com.kyung.marondalgram.user.service.UserService;

@Service
public class CommentService {
	private CommentRepository commentRepository;
	private UserService userService;
	public CommentService(CommentRepository commentRepository, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	// 댓글 추가
	public Comment additionCommentService(int userId, int postId, String comment) {
		Comment result = Comment.builder()
						.userId(userId)
						.postId(postId)
						.comment(comment)
						.build();
		
		 return commentRepository.save(result);
	}
	
	// commentDto로 변환
	public CommentDto changetoCommentDto(Comment comment) {
		UserDto user = userService.userData(comment.getUserId());
		
		CommentDto commentDto = CommentDto.builder()
								.id(comment.getId())
								.userId(comment.getUserId())
								.loginId(user.getLoginId())
								.postId(comment.getPostId())
								.comment(comment.getComment())
								.createdAt(comment.getCreatedAt())
								.updatedAt(comment.getUpdatedAt())
								.build();
		return commentDto;
	}
	
	// 댓글 목록 조회
	public List<CommentDto> getCommentList(int postId){
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentDto> commentDtoList = new ArrayList<>();
		for(Comment comment:commentList) {
			commentDtoList.add(changetoCommentDto(comment));
		}
		return commentDtoList;
		
	}
	
	// 댓글 갯수 조회
	public int commentCountService(int postId) {
		return commentRepository.countByPostId(postId);
	}
	
	// 댓글 삭제
	public boolean deleteCommentService(int commentId) {
		
		int result = commentRepository.countById(commentId);
		
		if(result > 0) {
			commentRepository.deleteById(commentId);
			return true;
		}else {
			return false;
		}
	}
	
	// 한 post 내의 댓글 모두 삭제
	public void deleteCommentPostAll(int postId) {
		commentRepository.deleteByPostId(postId);
		
	}
}
