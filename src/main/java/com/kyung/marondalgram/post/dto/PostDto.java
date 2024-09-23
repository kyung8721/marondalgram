package com.kyung.marondalgram.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {
	
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	private int musicId;
	
	private String loginId; // 게시글 작성자의 닉네임
	private String profileImagePath; // 게시글 작성자의 프로필 이미지 경로
	
	public boolean like;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
}
