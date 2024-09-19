package com.kyung.marondalgram.post.dto;

import java.time.LocalDateTime;

import com.kyung.marondalgram.post.domain.Post;

import lombok.Getter;

@Getter
public class PostDto {
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	private int musicId;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
//	public static Post toEntity(PostDto dto) {
//		return Post.builder()
//				.id(dto.getId())
//				.
//	}
}
