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
	
	private String loginId;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

}
